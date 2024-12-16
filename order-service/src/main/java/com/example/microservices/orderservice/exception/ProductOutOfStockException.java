package com.example.microservices.orderservice.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String skuCode) {
        super("Product with skuCode: " + skuCode + " is not in stock");
    }
}
