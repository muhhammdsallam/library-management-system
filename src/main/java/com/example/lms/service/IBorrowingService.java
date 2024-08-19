package com.example.lms.service;

import com.example.lms.dto.BorrowingRecordDTO;
import java.util.List;
public interface IBorrowingService {
    BorrowingRecordDTO borrowBook(Long bookId, Long patronId);
    BorrowingRecordDTO returnBook(Long bookId, Long patronId);
}
