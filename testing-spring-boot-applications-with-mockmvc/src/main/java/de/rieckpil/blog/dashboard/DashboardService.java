package de.rieckpil.blog.dashboard;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {
  public Integer[] getAnalyticsGraphData() {
    return new Integer[]{1, 2, 3, 4, 5, 6};
  }
}
