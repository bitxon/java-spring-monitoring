package bitxon.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class PriceRestClientConfig {

    @Bean
    public PriceRestClient priceRestClient(@Value("${http.price-client.url}") String url,
                                         RestClient.Builder restClientBuilder) {
        var restClient = restClientBuilder.baseUrl(url)
            .build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
            .build();
        return factory.createClient(PriceRestClient.class);
    }
}
