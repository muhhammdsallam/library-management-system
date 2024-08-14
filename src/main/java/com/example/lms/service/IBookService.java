package com.example.lms.service;

import com.example.lms.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book saveBook(Book book);
    Optional<Book> updateBook(Long id, Book book);
    void deleteBook(Long id);
}
