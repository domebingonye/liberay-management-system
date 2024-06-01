package com.maids.cc.library_management_system.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecords {
    private Long id;
    private Long patronId;
    private Long bookId;
    private Boolean borrow;
    private Boolean returned;
}
