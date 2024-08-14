package com.example.lms.service;

import com.example.lms.dto.BookDTO;
import com.example.lms.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookDTO> findAll();
    BookDTO findById(Long id);
    BookDTO saveBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
}
