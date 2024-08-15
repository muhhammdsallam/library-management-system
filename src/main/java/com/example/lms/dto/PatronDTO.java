package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatronDTO {
    private Long id;
    @NotBlank(message = "patron name cannot be blank")
    @NotNull(message = "patron name cannot be null")
    private String name;

    @NotBlank(message = "mobile number cannot be blank")
    @NotNull(message = "mobile number cannot be null")
    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters")
    @Pattern(regexp = "\\d+", message = "Mobile number must be numeric")
    private String mobileNumber;
}
