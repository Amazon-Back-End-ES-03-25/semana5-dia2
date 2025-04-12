package com.ironhack.semana5_dia2.service;


import com.ironhack.semana5_dia2.dto.BookRequestDTO;
import com.ironhack.semana5_dia2.dto.BookUpdateRequestDTO;
import com.ironhack.semana5_dia2.exception.BookNotFoundException;
import com.ironhack.semana5_dia2.model.Book;
import com.ironhack.semana5_dia2.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> bookArrayList = new ArrayList<>();
    private Long idCounter = 1L;

    public BookService() {
        bookArrayList = new ArrayList<>(List.of(
                new Book(nextId(), "1984", "George Orwell", Category.DYSTOPIA, 328),
                new Book(nextId(), "Brave New World", "Aldous Huxley", Category.DYSTOPIA, 268),
                new Book(nextId(), "Clean Code", "Robert C. Martin", Category.PROGRAMMING, 464),
                new Book(nextId(), "The Hobbit", "J.R.R. Tolkien", Category.FANTASY, 310),
                new Book(nextId(), "The Silmarillion", "J.R.R. Tolkien", Category.FANTASY, 365)
        ));
    }

    private Long nextId() {
        return idCounter++;
    }

    public List<Book> getAllBooks() {
        return bookArrayList;
    }

    public Book getBookById(Long id) {
        for (Book book : bookArrayList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new BookNotFoundException(id);
    }

    public List<Book> searchBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            return bookArrayList;
        }

        List<Book> result = new ArrayList<>();
        for (Book book : bookArrayList) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> filterBooks(List<Category> categories, int minPages, int maxPages) {
        List<Book> result = new ArrayList<>();

        for (Book book : bookArrayList) {
            boolean matchesCategory = (categories == null || categories.isEmpty() || categories.contains(book.getCategory()));
            boolean matchesPages = (book.getPages() >= minPages && book.getPages() <= maxPages);

            if (matchesCategory && matchesPages) {
                result.add(book);
            }
        }

        return result;
    }

    public Book addBook(Book book) {
        book.setId(nextId());
        bookArrayList.add(book);
        return book;
    }

    public Book addBookFromDto(BookRequestDTO dto) {
        Book book = new Book(nextId(), dto.getTitle(), dto.getAuthor(), dto.getCategory(), dto.getPages());
        bookArrayList.add(book);
        return book;
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookArrayList.remove(book); // podemos borrar en vez de por id por Objeto
    }

    public Book updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(bookRequestDTO.getTitle());
        existingBook.setAuthor(bookRequestDTO.getAuthor());
        existingBook.setCategory(bookRequestDTO.getCategory());
        existingBook.setPages(bookRequestDTO.getPages());
        return existingBook;
    }

    public Book updatePartialBook(Long id, BookUpdateRequestDTO bookRequestDTO) {
        Book existingBook = getBookById(id);
        if (bookRequestDTO.getTitle() != null) existingBook.setTitle(bookRequestDTO.getTitle());
        if (bookRequestDTO.getAuthor() != null) existingBook.setAuthor(bookRequestDTO.getAuthor());
        if (bookRequestDTO.getCategory() != null) existingBook.setCategory(bookRequestDTO.getCategory());
        if (bookRequestDTO.getPages() != null) existingBook.setPages(bookRequestDTO.getPages());
        return existingBook;
    }

    public Book updateBookCategory(Long id, Category newCategory) {
        Book existingBook = getBookById(id);
        existingBook.setCategory(newCategory);
        return existingBook;
    }
}
