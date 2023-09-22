package bitxon.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class PriceWebClientConfig {

    @Bean
    public PriceWebClient priceWebClient(@Value("${http.price-client.url}") String url,
                                         WebClient.Builder webClientBuilder) {
        var client = webClientBuilder.baseUrl(url)
            .build();
        var factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.forClient(client))
            .build();
        return factory.createClient(PriceWebClient.class);
    }
}
