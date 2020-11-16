package de.rieckpil.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

  @GetMapping
  public String getDashboardView(Model model) {
    model.addAttribute("message", "Hello World!");
    model.addAttribute("orderIds", Arrays.asList(42, 24, 72));
    return "dashboard";
  }
}
