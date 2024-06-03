package com.maids.cc.library_management_system.domain;

import lombok.Data;

@Data
public class LoginRequest {
    private String password;
    private String username;
}
