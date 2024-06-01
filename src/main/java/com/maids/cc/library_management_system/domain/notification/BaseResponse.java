package com.maids.cc.library_management_system.domain.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private int statusCode;
    private String message;
    private Object response;
}
