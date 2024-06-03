package com.maids.cc.library_management_system.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecords {
    private Long id;
    private Long patronId;
    private Long bookId;
    @NotNull(message = "borrow is required")
    private Boolean borrow;
    private Boolean returned;
}
