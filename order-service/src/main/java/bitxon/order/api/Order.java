package bitxon.order.api;

public record Order(
    String productIdentifier,
    String productName,
    int quantity
) {}
