package com.ironhack.semana5_dia1.controller;

import com.ironhack.semana5_dia1.dto.BookRequestDTO;
import com.ironhack.semana5_dia1.dto.BookResponseDTO;
import com.ironhack.semana5_dia1.model.Book;
import com.ironhack.semana5_dia1.model.Category;
import com.ironhack.semana5_dia1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    public List<Book> searchBooksByAuthor(@RequestParam(required = false) String author) {
        return bookService.searchBooksByAuthor(author);
    }

    @GetMapping("/filter")
    public List<Book> filterBooks(
            @RequestParam(required = false) List<Category> categories,
            @RequestParam(defaultValue = "0") int minPages,
            @RequestParam(defaultValue = "1000") int maxPages
    ) {
        return bookService.filterBooks(categories, minPages, maxPages);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/book-dto")
    public BookResponseDTO createBookWithDto(@RequestBody @Valid BookRequestDTO dto) {
        Book book = bookService.addBookFromDto(dto);
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor());
    }
}
