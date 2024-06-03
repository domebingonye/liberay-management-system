package com.maids.cc.library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Accessors(chain = true)
@Data
@NoArgsConstructor
@Table(name = "BORROWING_RECORDS")
public class BorrowingRecordsEntity extends AuditModel{
    @Column(name = "PATRON_ID", nullable = false)
    private Long patronId;

    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "BORROW", nullable = false)
    private Boolean borrow;

    @Column(name = "RETURNED")
    private Boolean returned;
}
