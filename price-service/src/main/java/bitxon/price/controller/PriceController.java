package bitxon.price.controller;

import bitxon.price.api.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceController {
    private static final Logger LOG = LoggerFactory.getLogger(PriceController.class);

    @GetMapping("/price/{identifier}")
    public Price getPrice(@PathVariable(name = "identifier") String identifier,
                          @RequestParam(name = "delay", required = false) Integer delay) throws InterruptedException {
        LOG.info("Handling: GET /price/{}?delay={}", identifier, delay);

        if (delay != null && delay > 0) {
            Thread.sleep(delay);
        }

        var amount = switch (identifier) {
            case "ipad" -> 499;
            case "iphone" -> 899;
            case "macbook" -> 1299;
            default -> 100;
        };

        return new Price(amount);

    }
}
