package de.rieckpil;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

  private static final List<Customer> CUSTOMERS = new ArrayList<>();

  @GetMapping
  public String getCustomerView(Model model) {

    model.addAttribute("customerFormObject", new CustomerFormObject());
    model.addAttribute("customers", CUSTOMERS);

    return "customers";
  }

  @PostMapping
  public String createCustomer(
    @Valid CustomerFormObject customerFormObject,
    BindingResult bindingResult,
    Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("customers", CUSTOMERS);
      return "customers";
    }

    CUSTOMERS.add(Customer.from(customerFormObject));

    return "redirect:/customers";
  }

  public record Customer(
    String name,
    String number,
    String email,
    LocalDateTime createdAt) {

    public static Customer from(CustomerFormObject customerFormObject) {
      return new Customer(customerFormObject.getName(), customerFormObject.getNumber(), customerFormObject.getEmail(), LocalDateTime.now());
    }
  }
}
