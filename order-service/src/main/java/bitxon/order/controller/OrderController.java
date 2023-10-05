package bitxon.order.controller;

import bitxon.order.api.FinalPrice;
import bitxon.order.api.Order;
import bitxon.order.client.PriceClient;
import bitxon.order.db.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final PriceClient priceClient;
    private final OrderDao orderDao;
    private final AsyncService asyncService;


    public OrderController(PriceClient priceClient, OrderDao orderDao, AsyncService asyncService) {
        this.priceClient = priceClient;
        this.orderDao = orderDao;
        this.asyncService = asyncService;
    }

    @PostMapping
    public FinalPrice publishOrder(@RequestBody Order order) {
        LOG.info("Handling: POST /order");

        var identifier = order.productIdentifier();
        var amount = priceClient.getPrice(identifier);

        int totalAmount = amount * order.quantity();

        orderDao.create(order.productIdentifier(), order.productName(), order.quantity(), totalAmount);

        asyncService.asyncMethod();

        return new FinalPrice(totalAmount);
    }
}
