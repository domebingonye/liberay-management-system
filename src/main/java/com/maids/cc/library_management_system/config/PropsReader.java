package com.maids.cc.library_management_system.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("propsReader")
@Data
public class PropsReader {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenValiditySeconds}")
    private long accessTokenValiditity;
}
