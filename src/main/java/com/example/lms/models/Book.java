package com.example.lms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "author name cannot be blank")
    private String author;

    @Min(value = 1500, message = "Publication year must be no earlier than 1500")
    @Max(value = 2024, message = "Publication year must be no later than 2024")
    private int publicationYear;

    @Column(length = 13, unique = true)
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Pattern(regexp = "\\d+", message = "ISBN must be numeric")
    @NotBlank(message = "isbn cannot be blank")
    private String isbn;

}
