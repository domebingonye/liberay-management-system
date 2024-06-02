package com.maids.cc.library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Data
@NoArgsConstructor
@Table(name = "BOOK")
public class BookEntity {
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "PUBLICATION", nullable = false)
    private String publication;

    @Column(name = "YEAR")
    private String year;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "BOOK_REG_ID", nullable = false)
    private String bookRegId;
}
