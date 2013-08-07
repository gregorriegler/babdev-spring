package com.blogspot.babdev.bookapi;

import com.blogspot.babdev.bookapi.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "authors", rel = "authors")
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {
}
