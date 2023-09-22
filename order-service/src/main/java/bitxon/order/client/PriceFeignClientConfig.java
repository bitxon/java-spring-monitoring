package bitxon.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceFeignClientConfig {
    @Bean
    public PriceFeignClient priceFeignClient(@Value("${http.price-client.url}") String url,
                                             ApplicationContext appContext) {
        var feignClientBuilder = new FeignClientBuilder(appContext);
        return feignClientBuilder.forType(PriceFeignClient.class, "price-client")
            .url(url)
            .build();
    }
}
