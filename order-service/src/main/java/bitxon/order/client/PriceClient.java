package bitxon.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PriceClient {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public PriceClient(@Value("${http.price-client.url}") String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }


    public int getPrice(String identifier, int delay) {
        var price = restTemplate.getForObject(
            "%s/price/%s?delay=%d".formatted(baseUrl, identifier, delay),
            Price.class
        );
        return price.amount();
    }

    public int getPrice(String identifier) {
        return getPrice(identifier, 0);
    }

    private record Price(int amount) {}
}
