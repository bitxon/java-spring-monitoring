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


    public int getPrice(String identifier) {
        var price = restTemplate.getForObject(
            baseUrl + "/price/" + identifier,
            Price.class
        );
        return price.amount();

    }

    private record Price(int amount) {}
}
