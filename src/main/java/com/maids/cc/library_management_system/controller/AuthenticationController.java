package com.maids.cc.library_management_system.controller;

import com.maids.cc.library_management_system.domain.LoginRequest;
import com.maids.cc.library_management_system.domain.LoginResponse;
import com.maids.cc.library_management_system.domain.SystemUser;
import com.maids.cc.library_management_system.service.AuthenticationService;
import com.maids.cc.library_management_system.service.SystemUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationController {
    private final SystemUserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SystemUser> create( @Valid @RequestBody SystemUser user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request, @Valid @RequestBody LoginRequest login) {
        return ResponseEntity.ok().body(authenticationService.processLogin(request, login));
    }
}
