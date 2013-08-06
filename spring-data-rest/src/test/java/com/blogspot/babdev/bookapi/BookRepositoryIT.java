package com.blogspot.babdev.bookapi;

import com.blogspot.babdev.bookapi.model.Book;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class BookRepositoryIT {

    private final String url = "http://localhost:8080/books";

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void PUTBookIT() throws Exception {
        final String bookUrl = url + "/123";
        restTemplate.put(bookUrl, "{\"title\": \"Clean Code\"}");
        Resource<Book> bookResource = get(bookUrl);
        assertNotNull(bookResource);
        assertEquals("Clean Code", bookResource.getContent().getTitle());
        assertNull(bookResource.getContent().getIsbn());
    }

    private Resource<Book> get(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Book>>() {
        }).getBody();
    }


}
