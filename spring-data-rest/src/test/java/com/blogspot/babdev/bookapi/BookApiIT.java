package com.blogspot.babdev.bookapi;

import com.blogspot.babdev.bookapi.model.Author;
import com.blogspot.babdev.bookapi.model.Book;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;

public class BookApiIT {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String authorsUrl = "http://localhost:8080/authors";
    private final String booksUrl = "http://localhost:8080/books";

    @Test
    public void testCreateBookWithAuthor() throws Exception {
        final URI authorUri = restTemplate.postForLocation(authorsUrl, sampleAuthor()); // create Author

        final URI bookUri = new URI(booksUrl + "/" + sampleBookIsbn);
        restTemplate.put(bookUri, sampleBook(authorUri.toString())); // create Book linked to Author

        Resource<Book> book = getBook(bookUri);
        assertNotNull(book);

        final URI authorsOfBookUri = new URI(book.getLink("books.Book.authors").getHref());
        Resource<List<Resource<Author>>> authors = getAuthors(authorsOfBookUri);
        assertNotNull(authors.getContent());
        assertFalse(authors.getContent().isEmpty()); // check if /books/0132350882/authors contains an author
    }

    private String sampleAuthor() {
        return "{\"name\":\"Robert C. Martin\"}";
    }

    private final String sampleBookIsbn = "0132350882";

    private String sampleBook(String authorUrl) {
        return "{\"title\":\"Clean Code\",\"authors\":[{\"rel\":\"authors\",\"href\":\"" + authorUrl + "\"}]}";
    }

    private Resource<Book> getBook(URI uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Book>>() {
        }).getBody();
    }

    private Resource<List<Resource<Author>>> getAuthors(URI uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<List<Resource<Author>>>>() {
        }).getBody();
    }
}
