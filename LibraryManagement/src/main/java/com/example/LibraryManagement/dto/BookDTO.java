package com.example.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {
    @NotBlank(message = "Title cannot be empty")
    @Size(min=3,
    message = "Title must contain at least 3 characters")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;
}
