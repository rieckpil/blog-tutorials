package de.rieckpil.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class ViewController {

  @GetMapping
  public String getWelcomePage(Model model) {
    model.addAttribute("message", "Hello World!");
    return "welcome";
  }
}
