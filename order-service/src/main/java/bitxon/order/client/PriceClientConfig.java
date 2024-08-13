package bitxon.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class PriceClientConfig {

    @Bean
    public PriceClient exchangeClient(@Value("${http.price-client.url}") String url, RestClient.Builder clientBuilder) {
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(Duration.ofSeconds(20));

        var restClient = clientBuilder
            .baseUrl(url)
            .requestFactory(requestFactory)
            .build();

        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(PriceClient.class);
    }
}
