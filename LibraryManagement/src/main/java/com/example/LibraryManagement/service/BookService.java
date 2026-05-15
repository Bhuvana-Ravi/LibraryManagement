package com.example.LibraryManagement.service;

import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.exception.BookNotFoundException;
import com.example.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public Book save(Book book){
        return repo.save(book);
    }

    public List<Book> getAll(){
        return repo.findAll();
    }

    public Book getById(int id){
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public Book update(int id, Book book){

        Book existingBook=repo.findById(id).orElse(null);

        assert existingBook != null;
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return repo.save(existingBook);
    }

    public void deleteById(int id){
        repo.deleteById(id);
    }

    public List<Book> getByTitle(String title){
        return repo.findByTitle(title);
    }

    public List<Book> getByAuthor(String author){
        return repo.findByAuthor(author);
    }
}
