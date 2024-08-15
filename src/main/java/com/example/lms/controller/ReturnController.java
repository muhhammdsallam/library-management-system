package com.example.lms.controller;

import com.example.lms.dto.BorrowingRecordDTO;
import com.example.lms.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/return")
public class ReturnController {
    @Autowired
    private BorrowingService borrowingService;

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordDTO recordDTO = borrowingService.returnBook(bookId, patronId);
        return new ResponseEntity<>(recordDTO, HttpStatus.OK);
    }
}
