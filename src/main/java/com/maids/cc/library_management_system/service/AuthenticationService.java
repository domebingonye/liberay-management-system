package com.maids.cc.library_management_system.service;

import com.maids.cc.library_management_system.domain.LoginRequest;
import com.maids.cc.library_management_system.domain.LoginResponse;
import com.maids.cc.library_management_system.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final SystemUserService userService;

    public LoginResponse processLogin(HttpServletRequest request, LoginRequest login){
        String username = login.getUsername();
        String password = login.getPassword();
        String remoteAddr = request.getRemoteAddr();

        log.info("Trying to login " + username + " from " + remoteAddr);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // if request is needed during authentication
        token.setDetails(new WebAuthenticationDetails(request));
        log.info("Token " + token);
        try {

            Authentication auth = authenticationManager.authenticate(token);

            log.info("Authorities for " + username + ": " + auth.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(",")));

            SecurityContextHolder.getContext().setAuthentication(auth);

            var user = userService.findByUsername(username);

            log.info("User " + user.getUsername() + ": login successful");
            var userDetails = (UserDetails) auth.getPrincipal();

            String jwt = jwtTokenUtil.generateJwtToken(auth);
            return LoginResponse.builder()
                    .token("Bearer " + jwt)
                    .email(user.getEmail())
                    .code(user.getCode())
                    .firstName(user.getFirstName())
                    .surname(user.getSurname())
                    .userRoleType(user.getUserRoleType())
                    .active(user.isActive())
                    .userDetails(userDetails)
                    .build();
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, " wrong login details");
        }
    }
}
