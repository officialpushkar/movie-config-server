package io.javabrains.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallBackCatalog")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie[] movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie[].class);
        //Movie[] movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie[].class);
        //Movie[] movie = webClient.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId()).retrieve().bodyToMono(Movie[].class).block();
        assert movie != null;
        return new CatalogItem(movie[0].getName(), "Test", rating.getRating());
    }

    private CatalogItem getFallBackCatalog(Rating rating) {
        return new CatalogItem("Movie not found", "", rating.getRating());
    }
}
