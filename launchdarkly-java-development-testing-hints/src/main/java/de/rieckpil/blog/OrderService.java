package de.rieckpil.blog;

public class OrderService {

  private final FeatureFlagClient featureFlagClient;

  public OrderService(FeatureFlagClient featureFlagClient) {
    this.featureFlagClient = featureFlagClient;
  }

  public void processOrder(String orderId) {
    if("plane".equals(featureFlagClient.getCurrentValue("primary-shipment-method", "duke"))) {
      // distribute via plane
    }else {
      // different implementation
    }
  }
}
