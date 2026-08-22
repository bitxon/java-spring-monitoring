package bitxon.order.config;

import io.micrometer.observation.ObservationPredicate;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.observation.ClientRequestObservationContext;
import org.springframework.http.server.observation.ServerRequestObservationContext;

import java.util.List;

@Configuration
public class TracingConfig {

    private static final List<String> EXCLUDED_URI_PREFIXES = List.of(
        "/actuator",
        "/instances"
    );

    /**
     * ObservedAspect makes @Observed annotation work
     */
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    ObservationPredicate noNoiseObservations() {
        return (name, context) -> {
            String uri = switch (context) {
                case ServerRequestObservationContext c -> c.getCarrier().getRequestURI();
                case ClientRequestObservationContext c -> c.getCarrier().getURI().getPath();
                default -> null;
            };
            return uri == null || EXCLUDED_URI_PREFIXES.stream().noneMatch(uri::startsWith);
        };
    }
}
