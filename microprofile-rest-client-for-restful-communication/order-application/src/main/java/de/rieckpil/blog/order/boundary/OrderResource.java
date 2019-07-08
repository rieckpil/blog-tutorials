package de.rieckpil.blog.order.boundary;

import de.rieckpil.blog.order.control.OrderService;
import de.rieckpil.blog.order.entity.Order;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("orders")
@Produces("application/json")
@Consumes("application/json")
public class OrderResource {

    @Inject
    private OrderService orderService;

    @GET
    @Path("/{id}")
    public JsonObject getOrderById(@PathParam("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @POST
    public Response createNewOrder(JsonObject order, @Context UriInfo uriInfo) {
        Integer newOrderId = this.orderService.createNewOrder(new Order(order));
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(newOrderId));

        return Response.created(uriBuilder.build()).build();
    }
}
