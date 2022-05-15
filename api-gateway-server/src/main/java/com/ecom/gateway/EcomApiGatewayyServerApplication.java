package com.ecom.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcomApiGatewayyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApiGatewayyServerApplication.class, args);
	}
	

	

}
