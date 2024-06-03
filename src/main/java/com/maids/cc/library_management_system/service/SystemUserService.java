package com.maids.cc.library_management_system.service;

import com.maids.cc.library_management_system.domain.SystemUser;
import com.maids.cc.library_management_system.enumerator.UserRoleType;
import com.maids.cc.library_management_system.model.SystemUserEntity;
import com.maids.cc.library_management_system.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemUserService implements UserDetailsService {
    private final SystemUserRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        var systemUser = findByUsername(userName);
        if (!ObjectUtils.isEmpty(systemUser) && StringUtils.hasText(systemUser.getUsername()) && systemUser.getUsername().equals(userName)) {
            GrantedAuthority authority = new SimpleGrantedAuthority(systemUser.getUserRoleType().name());

            return new User(systemUser.getUsername(), systemUser.getPassword(), Collections.singletonList(authority));
        } else throw new UsernameNotFoundException("User with " + userName + " not found");
    }

    public SystemUser findByUsername(String userName) {
        return repository.findByUsername(userName).map(systemUserEntity -> modelMapper.map(systemUserEntity, SystemUser.class)).orElse(null);
    }

    public SystemUser findByEmail(String email) {
        return repository.findByEmail(email).map(systemUserEntity -> modelMapper.map(systemUserEntity, SystemUser.class)).orElse(null);
    }

    public SystemUser create(SystemUser systemUser) {
        if (findByUsername(systemUser.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username " + systemUser.getUsername() + " exists");
        }

        if (findByEmail(systemUser.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The email " + systemUser.getEmail() + " exists");
        }
        String generatedPassword = !StringUtils.hasText(systemUser.getPassword()) ? RandomStringUtils.randomAlphanumeric(10) : systemUser.getPassword();

        systemUser.setPassword(passwordEncoder.encode(generatedPassword));
        systemUser.setCode(userCode(systemUser.getUsername()));
        systemUser.setUsername(systemUser.getUsername());
        systemUser.setSurname(systemUser.getSurname());
        systemUser.setUserRoleType(!ObjectUtils.isEmpty(systemUser.getUserRoleType()) ? systemUser.getUserRoleType() : UserRoleType.USER);

        var result = save(systemUser);
        log.info("Result" + result);
        systemUser.setId(result.getId());
        systemUser.setCode(result.getCode());
        return systemUser;
    }

    private static String userCode(String userName) {
        return userName + LocalDateTime.now().getNano();
    }

    public SystemUser save(SystemUser systemUser) {
        var response = repository.save(modelMapper.map(systemUser, SystemUserEntity.class));
        log.info("SAVING USER INFO::: ::: " + systemUser);

        return modelMapper.map(response, SystemUser.class);
    }
}
