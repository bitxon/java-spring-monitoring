package bitxon.order.client;

import bitxon.order.client.model.Price;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface PriceRestClient {

    @GetExchange("/price/{identifier}")
    Price getPrice(@PathVariable("identifier") String identifier);
}
