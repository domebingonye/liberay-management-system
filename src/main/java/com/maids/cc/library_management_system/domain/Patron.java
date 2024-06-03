package com.maids.cc.library_management_system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class Patron {
    @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "contact is required")
    private String contact;
    @NotBlank(message = "information is required")
    private String information;
    private String patronRegId;
}
