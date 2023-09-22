package bitxon.order.client;

import bitxon.order.client.tracing.interceptor.RestClientTracingInterceptor;
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
                                           RestClient.Builder restClientBuilder,
                                           RestClientTracingInterceptor restClientTracingInterceptor) {
        var restClient = restClientBuilder.baseUrl(url)
            .requestInterceptor(restClientTracingInterceptor)
            .build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
            .build();
        return factory.createClient(PriceRestClient.class);
    }
}
