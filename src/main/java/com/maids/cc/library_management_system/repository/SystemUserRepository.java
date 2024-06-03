package com.maids.cc.library_management_system.repository;

import com.maids.cc.library_management_system.model.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {
    Optional<SystemUserEntity> findByUsernameOrEmail(String username, String email);
    Optional<SystemUserEntity> findByUsername(String username);
    Optional<SystemUserEntity> findByEmail(String email);
    Optional<SystemUserEntity> findByCode(String code);
}
