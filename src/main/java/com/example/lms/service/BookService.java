package com.example.lms.service;

import com.example.lms.dto.BookDTO;
import com.example.lms.exceptions.EntityAlreadyExistsException;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Book;
import com.example.lms.repository.BookRepository;
import com.example.lms.utils.BookMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "books")
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return BookMapper.toDTO(book);
    }
    @Transactional
    @Override
    public BookDTO saveBook(BookDTO bookDTO){
            Optional<Book> existingBook = bookRepository.findByIsbn(bookDTO.getIsbn());
            if(existingBook.isPresent()){
                throw new EntityAlreadyExistsException("Book with the same ISBN already exists");
            }

            Book book = BookMapper.toEntity(bookDTO);
            try {
                Book savedBook = bookRepository.save(book);
                return BookMapper.toDTO(savedBook);
            }
            catch (ConstraintViolationException e){
                throw new IllegalArgumentException(e.getMessage());
            }
    }
    @Transactional
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO){
        try {
            Book existingBook = bookRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Book not found"));
            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setAuthor(bookDTO.getAuthor());
            existingBook.setPublicationYear(bookDTO.getPublicationYear());
            existingBook.setIsbn(bookDTO.getIsbn());

            Book updatedBook = bookRepository.save(existingBook);
            return BookMapper.toDTO(updatedBook);
        }
        catch (ConstraintViolationException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Transactional
    @Override
    public void deleteBook(Long id){
        // TODO add cannot remove if borrowed
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("book not found");
        }
        bookRepository.deleteById(id);
    }
}
