package de.rieckpil.blog;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class HealthUpdates implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        // HealthCheckResponse.named("my-liveness-check").up().build();
        // HealthCheckResponse.builder().name("my-liveness-check").up().build();
        return HealthCheckResponse.up("my-liveness-check");
    }
}
