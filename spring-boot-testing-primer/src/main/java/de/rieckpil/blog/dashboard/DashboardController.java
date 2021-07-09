package de.rieckpil.blog.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

  @GetMapping
  public String getDashboard(Model model) {
    model.addAttribute("message", "Hello World!");
    return "dashboard";
  }
}
