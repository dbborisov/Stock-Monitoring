package net.lesno.stock.services.services.imp;


import net.lesno.stock.entitys.model.RevolutStockListPrice;
import net.lesno.stock.services.services.RestApiService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class RestApiServiceImpl implements RestApiService {


    private final RestTemplate restTemplate;

    public RestApiServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public String getPostsPlainJSON(String symbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&outputsize=full&apikey=49KXBTD3FBBZ3WL2";
        return this.restTemplate.getForObject(url, String.class);
    }

    public RevolutStockListPrice[] getPostsAsObject(String symbol) {
        String url = "https://jsonplaceholder.typicode.com/posts";
//        Post[] posts = this.restTemplate.getForObject(url, Post[].class);
//        for (int i = 0; i <posts.length; i++) {
//            System.out.println(posts[i].getBody() + " "+posts[i].getTitle() + " "+posts[i].getUserId() + " ");
//        }
        return this.restTemplate.getForObject(url, RevolutStockListPrice[].class);
    }

    @Deprecated
    public Post getPostWithResponseHandling() {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        ResponseEntity<Post> response = this.restTemplate.getForEntity(url, Post.class, 1);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

}
