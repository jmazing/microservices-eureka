package com.microservices.itemrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
// @EnableFeignClients
public class ItemsrequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemsrequestApplication.class, args);
	}

}
