package io.javabrains.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatUserController {



    @Autowired
    private RestTemplate restTemplate;
 
    @GetMapping("/invokeChat")
    public String getChatInfo() {

    	return restTemplate.getForObject("http://chat-service/getChat", String.class);
        
    }
}
