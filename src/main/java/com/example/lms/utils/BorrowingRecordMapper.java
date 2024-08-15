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

//    public static BorrowingRecord toEntity(BorrowingRecordDTO borrowingRecordDTO) {
//        if (borrowingRecordDTO == null) {
//            return null;
//        }
//
//        BorrowingRecord borrowingRecord = new BorrowingRecord();
//        borrowingRecord.setId(borrowingRecordDTO.getId());
//        // Assuming you have relationships set up
//        borrowingRecord.setBook(null); // Needs to be retrieved from repository
//        borrowingRecord.setPatron(null); // Needs to be retrieved from repository
//        borrowingRecord.setBorrowingDate(borrowingRecordDTO.getBorrowingDate());
//        borrowingRecord.setReturnDate(borrowingRecordDTO.getReturnDate());
//
//        return borrowingRecord;
//    }
}
