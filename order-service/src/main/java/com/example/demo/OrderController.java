package com.example.demo;

import com.example.demo.PaymentClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger logger =
            LoggerFactory.getLogger(OrderController.class);

    private final PaymentClient paymentClient;

    public OrderController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @GetMapping("/{orderId}")
    @Retry(name = "paymentService", fallbackMethod = "paymentFallback")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    //@TimeLimiter(name = "paymentService")
    public String placeOrder(@PathVariable String orderId) {
    	logger.info("Received order request for orderId: {}", orderId);
       // System.out.println("Calling Payment Service...");
        String paymentResponse = paymentClient.processPayment(orderId);
        logger.info("Payment response for orderId {} : {}", orderId, paymentResponse);
        return "Order placed successfully. " + paymentResponse;
    }

    public String paymentFallback(String orderId, Exception ex) {
        return "Payment Service is down. Please try again later.";
    }
}