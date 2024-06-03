package com.maids.cc.library_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maids.cc.library_management_system.enumerator.UserRoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class SystemUser {
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    private Long id;
    @NotBlank(message = "Email is required.")
    private String email;
    @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    private String code;
    @Getter(onMethod = @__(@JsonIgnore))
    @Setter
    private String password;
    private String surname;
    private String firstName;
    @NotBlank(message = "Username is required.")
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, hidden = true)
    private UserRoleType userRoleType = UserRoleType.USER;
    private String loginUrl;
    private boolean active = true;
}
