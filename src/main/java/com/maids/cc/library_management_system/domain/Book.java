package com.maids.cc.library_management_system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class Book {
    @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "author is required")
    private String author;
    @NotBlank(message = "publication is required")
    private String publication;
    private String year;
    private String isbn;
    private String bookRegId;
}
