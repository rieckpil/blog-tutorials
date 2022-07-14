package de.rieckpil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping
  public String getAdminView(Model model) {
    model.addAttribute("secretMessage", "Duke 42");
    return "admin";
  }
}
