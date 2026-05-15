package com.example.LibraryManagement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min=3,message = "Title must contain at least 3 characters")
    @Schema(name = "title", example = "Ponniyin Selvan")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;
}
