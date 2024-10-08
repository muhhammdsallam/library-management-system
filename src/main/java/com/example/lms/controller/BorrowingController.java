package com.example.lms.controller;

import com.example.lms.dto.BorrowingRecordDTO;
import com.example.lms.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/borrow")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordDTO recordDTO = borrowingService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(recordDTO, HttpStatus.CREATED);
    }

}
