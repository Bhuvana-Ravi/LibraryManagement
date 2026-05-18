package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.service.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.LibraryManagement.utils.JwtUtil;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping
    public Book save(@Valid @RequestBody BookDTO dto){
        Book book=new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());

        return service.save(book);
    }

    @GetMapping
    public List<Book> getAll(){
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Retrieved Successfully"),
            @ApiResponse(responseCode = "404", description = "Book was not found")
    })
    @GetMapping("/{id}")
    public Book getById(@PathVariable int id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id,
                           @RequestBody Book book){
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){
        service.deleteById(id);
        return "Book has been deleted successfully";
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return service.getByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return service.getByAuthor(author);
    }

    @GetMapping("/token")
    public String generateToken(){
        return jwtUtil.generateToken("admin");
    }

    @GetMapping("/validate")
    public String validate(
            @RequestParam String token
    ){
        boolean valid=jwtUtil.validateToken(token);
        if(valid){
            return "Valid Token";
        }
        return "Invalid Token";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Library management API Running";
    }


}
