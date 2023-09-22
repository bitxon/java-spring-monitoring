package bitxon.order.client;

import bitxon.order.client.model.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient
public interface PriceFeignClient {

    @GetMapping("/price/{identifier}")
    Price getPrice(@PathVariable("identifier") String identifier);
}
