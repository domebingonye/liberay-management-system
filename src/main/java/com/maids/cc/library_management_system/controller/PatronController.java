package com.maids.cc.library_management_system.controller;

import com.maids.cc.library_management_system.domain.Book;
import com.maids.cc.library_management_system.domain.Patron;
import com.maids.cc.library_management_system.domain.notification.BaseResponse;
import com.maids.cc.library_management_system.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/patron")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;

    @PostMapping
    public ResponseEntity<Patron> createBook(@RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.createPatron(patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable final Long id, @RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.updatePatron(id, patron));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getBookById(@PathVariable final Long id) {
        return ResponseEntity.ok(patronService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatron() {
        return ResponseEntity.ok(patronService.getAllPatron());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBookById(@PathVariable final Long id) {
        return ResponseEntity.ok(patronService.deleteById(id));
    }
}
