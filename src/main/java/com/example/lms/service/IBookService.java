package com.example.lms.service;

import com.example.lms.dto.BookDTO;
import com.example.lms.models.Book;

import java.util.List;
public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
}
