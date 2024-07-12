package com.bansira.librarymanagementsystem.controller;

import com.bansira.librarymanagementsystem.entity.Book;
import com.bansira.librarymanagementsystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<Void> removeBook(@PathVariable String isbn) {
        libraryService.removeBook(isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/title/{title}")
    public ResponseEntity<List<Book>> findBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(libraryService.findBookByTitle(title));
    }

    @GetMapping("/book/author/{author}")
    public ResponseEntity<List<Book>> findBookByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(libraryService.findBookByAuthor(author));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> listAllBooks() {
        return ResponseEntity.ok(libraryService.listAllBooks());
    }

    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> listAvailableBooks() {
        return ResponseEntity.ok(libraryService.listAvailableBooks());
    }
}
