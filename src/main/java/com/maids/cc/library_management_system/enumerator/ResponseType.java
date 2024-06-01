package com.maids.cc.library_management_system.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResponseType {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    SUCCESS_MESSAGE("Successful"),
    DELETED_MESSAGE("Deleted");

    @Getter
    private final String response;
}
