package com.microservices.itemrequest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.itemrequest.interfaces.ItemService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ItemsController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired 
    private ItemService itemService;

    // Without the feign library
    @GetMapping("/getItemsNoFeign")
    public ResponseEntity<String> callItemMicroserviceNoFeign() {

        InstanceInfo service = eurekaClient.getApplication("items-service")
                                            .getInstances()
                                            .get(0);
        
        String hostname = service.getHostName();
        int port = service.getPort();

        String uri = String.format("http://%s:%d/items", hostname, port);
        WebClient client = WebClient.builder()
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build();
        WebClient.ResponseSpec responseSpec = client.get().uri(uri).retrieve();
        String responseBody = responseSpec.bodyToMono(String.class).block();
        return new ResponseEntity<String>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/getItemsWithFeign")
    public ResponseEntity<String> callItemMicroserviceWithFeign() {

        ResponseEntity<String> resp = itemService.items();
        System.out.println("response with feign ========================================");
        System.out.println(resp);
        System.out.println("response with feign ========================================");
        return resp;
    }

    
    @GetMapping("/test")
    public void testCall() {
        System.out.println("IN HERE");
    }

    @GetMapping("/test2")
    public ResponseEntity<String> testCall2() {
        return new ResponseEntity<String>("IN TEST2", HttpStatus.OK);
    }
}