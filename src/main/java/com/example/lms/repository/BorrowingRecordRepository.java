package com.example.lms.repository;

import com.example.lms.models.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
    Optional<BorrowingRecord> findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);
}
