package com.bansira.librarymanagementsystem.service;

import com.bansira.librarymanagementsystem.entity.Book;
import com.bansira.librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LibraryServiceTest {

    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setIsbn("12345");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = libraryService.addBook(book);
        assertEquals("12345", savedBook.getIsbn());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testRemoveBook() {
        Book book = new Book();
        book.setIsbn("12345");
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        libraryService.removeBook("12345");
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testFindBookByTitle() {
        Book book = new Book();
        book.setTitle("Test Title");
        when(bookRepository.findByTitleContainingIgnoreCase("Test Title")).thenReturn(Collections.singletonList(book));

        assertEquals(1, libraryService.findBookByTitle("Test Title").size());
    }

    @Test
    void testFindBookByAuthor() {
        Book book = new Book();
        book.setAuthor("Test Author");
        when(bookRepository.findByAuthorContainingIgnoreCase("Test Author")).thenReturn(Collections.singletonList(book));

        assertEquals(1, libraryService.findBookByAuthor("Test Author").size());
    }

    @Test
    void testListAllBooks() {
        Book book = new Book();
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        assertEquals(1, libraryService.listAllBooks().size());
    }

    @Test
    void testListAvailableBooks() {
        Book book = new Book();
        book.setAvailability(true);
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        assertEquals(1, libraryService.listAvailableBooks().size());
    }
}
