package bitxon.order.controller;

import bitxon.order.client.PriceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncService.class);

    private final PriceClient priceClient;

    public AsyncService(PriceClient priceClient) {
        this.priceClient = priceClient;
    }

    @Async("asyncTaskExecutor")
    public void asyncMethod() {
        priceClient.getPrice("unknown", /*delay*/100); // Pretend this is long-running operation
        LOG.info("Async log");
    }
}
