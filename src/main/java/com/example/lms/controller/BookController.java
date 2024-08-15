package com.example.lms.controller;

import com.example.lms.dto.BookDTO;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.exceptions.ErrorMessage;
import com.example.lms.models.Book;
import com.example.lms.service.BookService;
import com.example.lms.utils.BookMapper;
import com.fasterxml.jackson.datatype.jdk8.WrappedIOException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> bookDTOs = bookService.findAll();
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BookDTO> addNewBook(@Valid @RequestBody BookDTO bookDTO){
        BookDTO addedBookDTO = bookService.saveBook(bookDTO);
        return new ResponseEntity<>(addedBookDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO ){
        BookDTO updatedBookDTO = bookService.updateBook(id, bookDTO);
        return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
