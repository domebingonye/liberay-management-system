package com.maids.cc.library_management_system.repository;

import com.maids.cc.library_management_system.interfaces.Id;
import com.maids.cc.library_management_system.model.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<PatronEntity, Long> {
    Optional<Id> findTopByOrderByIdDesc();
    Optional<PatronEntity> findByPatronRegId(String patronRegId);
}
