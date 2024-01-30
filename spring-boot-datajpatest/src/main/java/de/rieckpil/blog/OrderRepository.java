package de.rieckpil.blog;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query(
      value =
          """
      SELECT *
      FROM orders
      WHERE items @> '[{"name": "MacBook Pro"}]';
    """,
      nativeQuery = true)
  List<Order> findAllContainingMacBookPro();

  List<Order> findAllByTrackingNumber(String trackingNumber);
}
