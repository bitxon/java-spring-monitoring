package bitxon.price.controller;

import bitxon.price.api.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PriceController {
    private static final Logger LOG = LoggerFactory.getLogger(PriceController.class);

    @GetMapping("/{identifier}")
    public Price getPrice(@PathVariable("identifier") String identifier) {
        LOG.info("Handling: GET /price/{}", identifier);

        var amount = switch (identifier) {
            case "ipad" -> 499;
            case "iphone" -> 899;
            case "macbook" -> 1299;
            default -> 100;
        };
        return new Price(amount);

    }
}
