package de.rieckpil.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookStoreController {

  @GetMapping("/book-store")
  public String getIndexPage(Model model) {
    model.addAttribute("message", "Web Tests with Selenide");
    return "index";
  }
}
