package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping("/{orderId}")
    public String processPayment(@PathVariable String orderId) {
    	
    	logger.info("Processing payment for orderId: {}", orderId);
        return "Payment successful for order: " + orderId + 
               " processed by instance running on port: " + port;
    }
}
