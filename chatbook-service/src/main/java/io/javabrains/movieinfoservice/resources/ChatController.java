package io.javabrains.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	
	@Value("${server.port}")
	private String port;

    @GetMapping("/getChat")
    public String getChat(){
        
    	return "Chat app running on "+port;
    	
    }
}
