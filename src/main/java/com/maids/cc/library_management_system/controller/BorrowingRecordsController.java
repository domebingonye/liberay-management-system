package com.maids.cc.library_management_system.controller;

import com.maids.cc.library_management_system.domain.BorrowingRecords;
import com.maids.cc.library_management_system.domain.Patron;
import com.maids.cc.library_management_system.service.BorrowingRecordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/borrowing-record")
@RequiredArgsConstructor
public class BorrowingRecordsController {
    private final BorrowingRecordsService borrowingRecordsService;

    @PostMapping
    public ResponseEntity<BorrowingRecords> createBorrowingRecords(@RequestParam String patronRegId, @RequestParam  String bookRegId) {
        return ResponseEntity.ok(borrowingRecordsService.createBorrowingRecords(patronRegId, bookRegId));
    }

    @PutMapping
    public ResponseEntity<BorrowingRecords> updateBorrowingRecordsById(@PathVariable final Long id, @Valid @RequestBody BorrowingRecords borrowingRecords) {
        return ResponseEntity.ok(borrowingRecordsService.updateBorrowingRecordsById(id, borrowingRecords));
    }
}
