package com.bansira.librarymanagementsystem;

import com.bansira.librarymanagementsystem.entity.Book;
import com.bansira.librarymanagementsystem.entity.Department;
import com.bansira.librarymanagementsystem.repository.DepartmentRepository;
import com.bansira.librarymanagementsystem.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class LibraryMenu implements CommandLineRunner {

    private final LibraryService libraryService;

    private final DepartmentRepository departmentRepository;

    public LibraryMenu(LibraryService libraryService, DepartmentRepository departmentRepository) {
        this.libraryService = libraryService;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Find Book by Title");
            System.out.println("4. Find Book by Author");
            System.out.println("5. List All Books");
            System.out.println("6. List Available Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    findBookByTitle(scanner);
                    break;
                case 4:
                    findBookByAuthor(scanner);
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
                    listAvailableBooks();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book publication year: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        System.out.print("Is the book available (true/false): ");
        boolean availability = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        Department department = departmentRepository.findFirstByName(departmentName);
        if (department == null) {
            department = new Department();
            department.setName(departmentName);
            department = departmentRepository.save(department);
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setGenre(genre);
        book.setPublicationYear(publicationYear);
        book.setAvailability(availability);
        book.setDepartment(department);

        libraryService.addBook(book);
        System.out.println("Book added successfully.");
    }

    private void removeBook(Scanner scanner) {
        System.out.print("Enter book ISBN to remove: ");
        String isbn = scanner.nextLine();
        libraryService.removeBook(isbn);
        System.out.println("Book removed successfully.");
    }

    private void findBookByTitle(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();
        libraryService.findBookByTitle(title).forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    private void findBookByAuthor(Scanner scanner) {
        System.out.print("Enter book author to search: ");
        String author = scanner.nextLine();
        libraryService.findBookByAuthor(author).forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    private void listAllBooks() {
        libraryService.listAllBooks().forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    private void listAvailableBooks() {
        libraryService.listAvailableBooks().forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }
}
