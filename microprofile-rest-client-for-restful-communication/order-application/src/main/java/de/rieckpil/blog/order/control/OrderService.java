package de.rieckpil.blog.order.control;

import de.rieckpil.blog.order.entity.Order;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class OrderService {

    private ConcurrentHashMap<Integer, Order> orderDatabase;

    @Inject
    @RestClient
    private UserManagementApplicationClient userManagementApplicationClient;

    @PostConstruct
    public void populateRandomOrders() {
        this.orderDatabase = new ConcurrentHashMap<>();
        this.orderDatabase.put(1, new Order(1, "MacBook", 1, 2, 1449.50));
        this.orderDatabase.put(2, new Order(2, "Smartphone", 2, 10, 345.90));
        this.orderDatabase.put(3, new Order(3, "PC", 3, 5, 777.00));
    }

    public Integer createNewOrder(Order order) {

        Integer nextOrderId = this.orderDatabase.size() + 1;
        order.setOrderId(nextOrderId);
        this.orderDatabase.put(nextOrderId, order);

        this.userManagementApplicationClient
                .createUser(Json.createObjectBuilder().add("userId", order.getUserId()).build());

        return order.getOrderId();
    }

    public JsonObject getOrderById(Integer orderId) {

        Order requestedOrder = orderDatabase.getOrDefault(orderId, defaultOrder());
        JsonObject user = this.userManagementApplicationClient.getUserById(UUID.randomUUID().toString(), requestedOrder.getUserId());

        return Json
                .createObjectBuilder(
                        requestedOrder.toJson())
                            .add("username", user.getString("username", "Default user"))
                .build();

    }

    private Order defaultOrder() {
        return new Order(-1, "Default Product", 1, 1, 100.00);
    }

}
