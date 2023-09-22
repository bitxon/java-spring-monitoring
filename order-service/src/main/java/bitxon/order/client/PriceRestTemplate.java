package bitxon.order.client;

import bitxon.order.client.model.Price;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PriceRestTemplate {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public PriceRestTemplate(@Value("${http.price-client.url}") String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }


    public Price getPrice(String identifier) {
        return restTemplate.getForObject(
            baseUrl + "/price/" + identifier,
            Price.class
        );

    }
}
