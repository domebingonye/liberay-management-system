package com.maids.cc.library_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.maids.cc.library_management_system.enumerator.UserRoleType;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String email;
    private String token;
    private String code;
    private String surname;
    private String firstName;
    private String username;
    private UserRoleType userRoleType;
    private boolean active;
    private UserDetails userDetails;
}
