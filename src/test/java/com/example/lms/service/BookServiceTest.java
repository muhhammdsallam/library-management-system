package com.example.lms.service;

import com.example.lms.dto.BookDTO;
import com.example.lms.exceptions.EntityAlreadyExistsException;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Book;
import com.example.lms.repository.BookRepository;
import com.example.lms.service.BookService;
import com.example.lms.utils.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("123456789");
        book.setPublicationYear(2020);

        bookDTO = BookMapper.toDTO(book);
    }

    @Test
    public void testFindById_BookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO foundBook = bookService.findById(1L);

        assertEquals("Test Book", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_BookDoesNotExist() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.findById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveBook_NewBook() {
        when(bookRepository.findByIsbn(bookDTO.getIsbn())).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO savedBook = bookService.saveBook(bookDTO);

        assertEquals("Test Book", savedBook.getTitle());
        verify(bookRepository, times(1)).findByIsbn(bookDTO.getIsbn());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testSaveBook_ExistingBook() {
        when(bookRepository.findByIsbn(bookDTO.getIsbn())).thenReturn(Optional.of(book));

        assertThrows(EntityAlreadyExistsException.class, () -> bookService.saveBook(bookDTO));
        verify(bookRepository, times(1)).findByIsbn(bookDTO.getIsbn());
        verify(bookRepository, times(0)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook_BookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        bookDTO.setTitle("Updated Title");
        BookDTO updatedBook = bookService.updateBook(1L, bookDTO);

        assertEquals("Updated Title", updatedBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook_BookDoesNotExist() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.updateBook(1L, bookDTO));
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(0)).save(any(Book.class));
    }

    @Test
    public void testDeleteBook_BookExists() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBook_BookDoesNotExist() {
        when(bookRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(0)).deleteById(1L);
    }
}
