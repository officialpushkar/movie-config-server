package io.javabrains.movieinfoservice.resources;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
/*
    @GetMapping("/{movieId}")
    public List<Movie> getCatalog(@PathVariable("movieId") String movieId){
        return Collections.singletonList(new Movie("101","Battleship","Test"));
    }*/


    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        MovieSummary movieSummary = restTemplate.getForObject("https://localhost:8000/pets/" + movieId , MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }

    @GetMapping("/getAllMoviess")
    public List<Movie> getAllMovies(){
        return Stream.of(new Movie("1","FastFurious1","Good Movie"),
                         new Movie("2","FastFurious2","Better Movie"),
                        new Movie("3","FastFurious3","Best Movie"))
                         .collect(Collectors.toList());
    }
}
