package com.ecom.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	
   /*
    * product service fallback / failure mapping and message
    * */
    @GetMapping("/productServiceFallBack")
    public String userServiceFallBackMethod() {
        return "Product Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/paymentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "Payment Service is taking longer than Expected." +
                " Please try again later";
    }
}
