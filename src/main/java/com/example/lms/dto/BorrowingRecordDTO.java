package com.example.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowingRecordDTO {
    private Long id;
    @NotNull(message = "book id cannot be null")
    private Long bookId;
    @NotNull(message = "patron id cannot be null")
    private Long patronId;
    @NotNull(message = "borrowing cannot be null")
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
