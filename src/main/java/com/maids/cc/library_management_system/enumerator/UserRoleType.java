package com.maids.cc.library_management_system.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserRoleType {
    USER(Code.ROLE_USER);

    @Getter
    private final String userType;

    public static class Code {
        public static final String ROLE_USER = "USER";
    }
}
