package com.ironhack.semana5_dia1.service;


import com.ironhack.semana5_dia1.dto.BookRequestDTO;
import com.ironhack.semana5_dia1.model.Book;
import com.ironhack.semana5_dia1.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long idCounter = 1L;

    public BookService() {
        books = new ArrayList<>(List.of(
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
        return books;
    }

    public Book getBookById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new NoSuchElementException("Book not found with id: " + id);
    }

    public List<Book> searchBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            return books;
        }

        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> filterBooks(List<Category> categories, int minPages, int maxPages) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
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
        books.add(book);
        return book;
    }

    public Book addBookFromDto(BookRequestDTO dto) {
        Book book = new Book(nextId(), dto.getTitle(), dto.getAuthor(), dto.getCategory(), dto.getPages());
        books.add(book);
        return book;
    }

}