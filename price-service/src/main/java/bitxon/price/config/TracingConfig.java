package bitxon.price.config;

import io.micrometer.tracing.exporter.SpanExportingPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    SpanExportingPredicate noActuator() {
        return span -> span.getTags().get("uri") == null || !span.getTags().get("uri").startsWith("/actuator");
    }
}
