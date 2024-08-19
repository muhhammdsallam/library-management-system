package com.example.lms.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotNull(message = "author name cannot be null")
    @NotBlank(message = "author name cannot be blank")
    private String author;


//    @NotNull(message = "publication year cannot be null")
//    @NotBlank(message = "publication year cannot be blank")
    @Min(value = 1500, message = "Publication year must be no earlier than 1500")
    @Max(value = 2024, message = "Publication year must be no later than 2024")
    private int publicationYear;

    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Pattern(regexp = "\\d+", message = "ISBN must be numeric")
    @NotNull(message = "isbn cannot be null")
    @NotBlank(message = "isbn cannot be blank")
    private String isbn;

    @NotNull(message = "Quantity cannot be null")
    @NotBlank(message = "Quantity cannot be blank")
    @Min(value = 0, message = "quantity must be at least 0")
    private int quantity;
}
