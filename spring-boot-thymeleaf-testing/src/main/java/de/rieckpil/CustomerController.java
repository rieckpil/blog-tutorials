package de.rieckpil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

  @GetMapping
  public String getCustomerView(Model model) {

    model.addAttribute("customerCreationForm", new CustomerCreationRequest("", "", ""));

    return "customers";
  }

  @PostMapping
  public String createCustomer(
    CustomerCreationRequest customerCreationRequest,
    BindingResult bindingResult,
    Model model) {

    if(bindingResult.hasErrors()) {
      return "customers";
    }

    // store customers

    return "redirect:/customers";
  }

  record CustomerCreationRequest(
    String customerName,
    String customerNumber,
    String customerEmailAddress
  ) {
  }
}
