package com.blogspot.babdev.bookapi;

import com.blogspot.babdev.bookapi.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
