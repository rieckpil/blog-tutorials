package de.rieckpil.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/index")
  public String getIndexPage(Model model) {
    model.addAttribute("message", "Integration Test with Selenium");
    return "index";
  }
}
