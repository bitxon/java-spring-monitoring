package bitxon.price.config;

import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.tracing.exporter.SpanExportingPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    SpanExportingPredicate noActuatorSpansForZipkin() {
        return span -> span.getTags().get("uri") == null || !span.getTags().get("uri").startsWith("/actuator");
    }

    @Bean
    MeterFilter noActuatorMetricsForPrometheus() {
        return MeterFilter.deny(id -> id.getTag("uri") != null && id.getTag("uri").startsWith("/actuator"));
    }
}
