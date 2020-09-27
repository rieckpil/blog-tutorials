package de.rieckpil.blog.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping
  public String getDashboardView(Model model) {

    model.addAttribute("user", "Duke");
    model.addAttribute("analyticsGraph", dashboardService.getAnalyticsGraphData());
    model.addAttribute("quickNote", new QuickNote());

    return "dashboard";
  }
}
