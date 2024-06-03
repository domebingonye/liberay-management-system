package com.maids.cc.library_management_system.controller;

import com.maids.cc.library_management_system.domain.Book;
import com.maids.cc.library_management_system.domain.notification.BaseResponse;
import com.maids.cc.library_management_system.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> registerBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.registerBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable final Long id, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBookById(id, book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable final Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBookById(@PathVariable final Long id) {
        return ResponseEntity.ok(bookService.deleteById(id));
    }
}
