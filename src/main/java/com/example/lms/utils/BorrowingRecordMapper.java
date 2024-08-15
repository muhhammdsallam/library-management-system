package com.example.lms.utils;

import com.example.lms.dto.BorrowingRecordDTO;
import com.example.lms.models.BorrowingRecord;

public class BorrowingRecordMapper {

    public static BorrowingRecordDTO toDTO(BorrowingRecord borrowingRecord) {
        if (borrowingRecord == null) {
            return null;
        }

        BorrowingRecordDTO borrowingRecordDTO = new BorrowingRecordDTO();
        borrowingRecordDTO.setId(borrowingRecord.getId());
        // Assuming you have getters and setters for these fields
        borrowingRecordDTO.setBookId(borrowingRecord.getBook().getId());  // Get book ID
        borrowingRecordDTO.setPatronId(borrowingRecord.getPatron().getId()); // Get patron ID
        borrowingRecordDTO.setBorrowingDate(borrowingRecord.getBorrowingDate());
        borrowingRecordDTO.setReturnDate(borrowingRecord.getReturnDate());

        return borrowingRecordDTO;
    }
}
