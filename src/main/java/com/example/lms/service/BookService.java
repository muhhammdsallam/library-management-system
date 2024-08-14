package com.example.lms.service;

import com.example.lms.exceptions.BookNotFoundException;
import com.example.lms.models.Book;
import com.example.lms.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public Optional<Book> findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }
    @Override
    public Book saveBook(Book book){
        try{
            return bookRepository.save(book);
        }
        catch (ConstraintViolationException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Override
    public Optional<Book> updateBook(Long id, Book bookDetails){
        return bookRepository.findById(id)
                .map(book -> {
                    book.setAuthor(bookDetails.getAuthor());
                    book.setTitle(bookDetails.getTitle());
                    book.setIsbn(bookDetails.getIsbn());
                    book.setPublicationYear(bookDetails.getPublicationYear());
                    try {
                        return Optional.of(bookRepository.save(book));
                    } catch (ConstraintViolationException e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                })
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
    @Override
    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("book not found");
        }
        bookRepository.deleteById(id);
    }
}
