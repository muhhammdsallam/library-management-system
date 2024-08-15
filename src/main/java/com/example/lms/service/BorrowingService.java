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
import com.example.lms.utils.BorrowingRecordMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingService implements IBorrowingService{
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public boolean isBookBorrowed(Long bookId) {
        return borrowingRecordRepository.existsByBookIdAndReturnDateIsNull(bookId);
    }

    @Transactional
    @Override
    public BorrowingRecordDTO borrowBook(Long bookId, Long patronId){

        if (isBookBorrowed(bookId)) {
            throw new EntityAlreadyReservedException("Book is already borrowed");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found"));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());

        BorrowingRecord savedRecord = borrowingRecordRepository.save(borrowingRecord);

        BorrowingRecordDTO borrowingRecordDTO = BorrowingRecordMapper.toDTO(savedRecord);

        return borrowingRecordDTO;
    }

    @Transactional
    public BorrowingRecordDTO returnBook(Long bookId, Long patronId){

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found"));

        if(isBookBorrowed(bookId)){
            BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                    .orElseThrow(() -> new EntityNotFoundException("Book is not borrowed by this patron"));
            borrowingRecord.setReturnDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);

            return BorrowingRecordMapper.toDTO(borrowingRecord);
        }else{
            throw new EntityNotFoundException("Book is not borrowed");
        }
    }
}
