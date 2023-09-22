package bitxon.order.controller;

import bitxon.order.api.FinalPrice;
import bitxon.order.api.Order;
import bitxon.order.client.PriceFeignClient;
import bitxon.order.client.PriceRestClient;
import bitxon.order.client.PriceRestTemplate;
import bitxon.order.client.PriceWebClient;
import bitxon.order.db.OrderDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final PriceRestTemplate priceRestTemplate;
    private final PriceRestClient priceRestClient;
    private final PriceWebClient priceWebClient;
    private final PriceFeignClient priceFeignClient;
    private final OrderDao orderDao;

    @PostMapping({"", "rest-template"})
    public FinalPrice publishOrderWithRestTemplate(@RequestBody Order order) {
        LOG.info("Handling: POST /order with RestTemplate");

        var identifier = order.productIdentifier();
        var amount = priceRestTemplate.getPrice(identifier).amount();

        int totalAmount = amount * order.quantity();

        orderDao.create(order.productIdentifier(), order.productName(), order.quantity(), totalAmount);

        return new FinalPrice(totalAmount);
    }

    @PostMapping({"rest-client"})
    public FinalPrice publishOrderWithRestClient(@RequestBody Order order) {
        LOG.info("Handling: POST /order with RestClient");

        var identifier = order.productIdentifier();
        var amount = priceRestClient.getPrice(identifier).amount();

        int totalAmount = amount * order.quantity();

        orderDao.create(order.productIdentifier(), order.productName(), order.quantity(), totalAmount);

        return new FinalPrice(totalAmount);
    }

    @PostMapping({"web-client"})
    public FinalPrice publishOrderWithWebClient(@RequestBody Order order) {
        LOG.info("Handling: POST /order withWebClient");

        var identifier = order.productIdentifier();
        var amount = priceWebClient.getPrice(identifier).amount();

        int totalAmount = amount * order.quantity();

        orderDao.create(order.productIdentifier(), order.productName(), order.quantity(), totalAmount);

        return new FinalPrice(totalAmount);
    }

    @PostMapping({"feign-client"})
    public FinalPrice publishOrderWithFeignClient(@RequestBody Order order) {
        LOG.info("Handling: POST /order withWebClient");

        var identifier = order.productIdentifier();
        var amount = priceFeignClient.getPrice(identifier).amount();

        int totalAmount = amount * order.quantity();

        orderDao.create(order.productIdentifier(), order.productName(), order.quantity(), totalAmount);

        return new FinalPrice(totalAmount);
    }
}
