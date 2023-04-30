package com.microservices.itemrequest.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("items-service")
public interface ItemService {
    
    @RequestMapping("/items")
    ResponseEntity<String> items();

}
