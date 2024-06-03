package com.maids.cc.library_management_system.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    public @NotNull Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || (authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.empty();
        }

        return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());

    }
}
