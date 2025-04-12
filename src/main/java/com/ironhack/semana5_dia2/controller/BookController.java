package com.ironhack.semana5_dia2.controller;

import com.ironhack.semana5_dia2.dto.BookRequestDTO;
import com.ironhack.semana5_dia2.dto.BookResponseDTO;
import com.ironhack.semana5_dia2.dto.BookUpdateRequestDTO;
import com.ironhack.semana5_dia2.model.Book;
import com.ironhack.semana5_dia2.model.Category;
import com.ironhack.semana5_dia2.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);  // Lanza un BookNotFoundException si no lo encuentra pero no hace falta un try-catch por el handler
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam(required = false) String author) {
        List<Book> books = bookService.searchBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooks(
            @RequestParam(required = false) List<Category> categories,
            @RequestParam(defaultValue = "0") int minPages,
            @RequestParam(defaultValue = "1000") int maxPages
    ) {
        List<Book> books = bookService.filterBooks(categories, minPages, maxPages);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(books); // 404 si no encuentra ningún libro (ejemplo)
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/book-dto")
    public ResponseEntity<BookResponseDTO> createBookWithDto(@RequestBody @Valid BookRequestDTO dto) {
        Book book = bookService.addBookFromDto(dto);
        BookResponseDTO responseDTO = new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequestDTO bookRequestDTO) {
        Book updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updatePartialBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequestDTO bookRequestDTO) {
        Book updatedBook = bookService.updatePartialBook(id, bookRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @PatchMapping("/{id}/category")
    public ResponseEntity<Book> updateBookCategory(@PathVariable Long id, @RequestBody @Valid Category newCategory) {
        Book updatedBook = bookService.updateBookCategory(id, newCategory);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }
}
