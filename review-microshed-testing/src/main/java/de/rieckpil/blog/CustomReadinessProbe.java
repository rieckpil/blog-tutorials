package de.rieckpil.blog;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class CustomReadinessProbe implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("ready")
                .up()
                .build();
    }
}
