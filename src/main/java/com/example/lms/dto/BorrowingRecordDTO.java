package com.example.lms.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowingRecordDTO {
    private Long id;
    private Long bookId;
    private Long patronId;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
