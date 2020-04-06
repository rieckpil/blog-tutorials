package de.rieckpil.blog;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/mix")
public class MixMediaTypeController {

  @GetMapping(path = "/orders", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public List<Order> getOrders() {
    List<Order> orders = List.of(
      new Order("42L", Set.of("foreign", "books"), 1L, LocalDateTime.now()),
      new Order("58B", Set.of("fractile", "computer"), 48L, LocalDateTime.now())
    );
    return orders;
  }

}
