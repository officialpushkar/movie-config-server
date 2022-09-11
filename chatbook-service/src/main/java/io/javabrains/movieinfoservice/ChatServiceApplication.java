package io.javabrains.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		// HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
		// HttpComponentsClientHttpRequestFactory();
		// clientHttpRequestFactory.setConnectionRequestTimeout(3000);
		return new RestTemplate();
	}
}
