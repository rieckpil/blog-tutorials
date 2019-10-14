package de.rieckpil.blog;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

import javax.inject.Inject;

public class MetricsUpdates {

    @Inject
    private MetricRegistry metricRegistry;

    public void updates() {
        Metadata metadata = Metadata
                .builder()
                .reusable(true)
                .withName("myMetric")
                .withUnit("seconds")
                .withDescription("counting seconds")
                .withType(MetricType.COUNTER)
                .build();

        Counter counterOne = metricRegistry.counter(metadata);
        Counter counterTwo = metricRegistry.counter(metadata);

    }
}
