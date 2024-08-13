package bitxon.order.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface PriceClient {

    @GetExchange("/price/{identifier}")
    Price getPrice(@PathVariable("identifier") String identifier);

    record Price(int amount) {}
}
