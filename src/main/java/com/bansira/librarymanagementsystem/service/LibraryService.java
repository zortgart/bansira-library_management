package com.bansira.librarymanagementsystem.service;

import com.bansira.librarymanagementsystem.repository.DepartmentRepository;
import com.bansira.librarymanagementsystem.entity.Book;
import com.bansira.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final BookRepository bookRepository;

    private final DepartmentRepository departmentRepository;

    public LibraryService(BookRepository bookRepository, DepartmentRepository departmentRepository) {
        this.bookRepository = bookRepository;
        this.departmentRepository = departmentRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(String isbn) {
        Optional<Book> book = bookRepository.findAll().stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        book.ifPresent(bookRepository::delete);
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findAll().stream().filter(Book::isAvailability).toList();
    }
}
