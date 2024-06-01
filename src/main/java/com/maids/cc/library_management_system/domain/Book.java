package com.maids.cc.library_management_system.domain;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publication;
    private String year;
    private String isbn;
    private String bookRegId;
}
