package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RefreshScope
public class CatalogResource {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${movie.info.service.endpoint}")
    private String movieInfoUrl;
//    @Bean
//    public WebClient.Builder getBuilder() {
//        return WebClient.builder();
//    }

//    @Autowired
//    private WebClient.Builder webClient;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private UserRatingService userRatingService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);
        return userRating.getRatings().stream().map(rating -> catalogService.getCatalogItem(rating)).collect(Collectors.toList());
    }

    @GetMapping("/getAllMovies")
    public String getAllMovies() {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(movieInfoUrl, String.class);
        return responseEntity.getBody();
    }
}
