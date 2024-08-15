package com.example.lms.service;

import com.example.lms.dto.BorrowingRecordDTO;
import com.example.lms.exceptions.EntityAlreadyReservedException;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Book;
import com.example.lms.models.BorrowingRecord;
import com.example.lms.models.Patron;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.BorrowingRecordRepository;
import com.example.lms.repository.PatronRepository;
import com.example.lms.service.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowingServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingService borrowingService;

    private Book book;
    private Patron patron;
    private BorrowingRecord borrowingRecord;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        patron = new Patron();
        patron.setId(1L);
        patron.setName("Test Patron");

        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());
    }

    @Test
    public void testBorrowBook_BookAlreadyBorrowed() {
        when(borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(1L)).thenReturn(true);

        assertThrows(EntityAlreadyReservedException.class, () -> borrowingService.borrowBook(1L, 1L));
        verify(borrowingRecordRepository, times(1)).existsByBookIdAndReturnDateIsNull(1L);
        verify(bookRepository, times(0)).findById(anyLong());
        verify(patronRepository, times(0)).findById(anyLong());
    }

    @Test
    public void testBorrowBook_Success() {
        when(borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(1L)).thenReturn(false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);

        BorrowingRecordDTO borrowingRecordDTO = borrowingService.borrowBook(1L, 1L);

        assertNotNull(borrowingRecordDTO);
        assertEquals(1L, borrowingRecordDTO.getBookId());
        assertEquals(1L, borrowingRecordDTO.getPatronId());
        assertNotNull(borrowingRecordDTO.getBorrowingDate());
        assertNull(borrowingRecordDTO.getReturnDate());
        verify(borrowingRecordRepository, times(1)).existsByBookIdAndReturnDateIsNull(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(patronRepository, times(1)).findById(1L);
        verify(borrowingRecordRepository, times(1)).save(any(BorrowingRecord.class));
    }

    @Test
    public void testReturnBook_BookBorrowed() {

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        when(borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(1L)).thenReturn(true);

        when(borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(1L, 1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> borrowingService.returnBook(1L, 1L));

        verify(borrowingRecordRepository, times(1)).existsByBookIdAndReturnDateIsNull(1L);
        verify(borrowingRecordRepository, times(1)).findByBookIdAndPatronIdAndReturnDateIsNull(1L, 1L);
    }


    @Test
    public void testReturnBook_BookNotBorrowed() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        when(borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> borrowingService.returnBook(1L, 1L));

        verify(borrowingRecordRepository, never()).findByBookIdAndPatronIdAndReturnDateIsNull(1L, 1L);
    }
}
