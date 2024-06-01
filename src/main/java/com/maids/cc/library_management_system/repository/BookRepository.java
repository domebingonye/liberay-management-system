package com.maids.cc.library_management_system.repository;

import com.maids.cc.library_management_system.interfaces.Id;
import com.maids.cc.library_management_system.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<Id> findTopByOrderByIdDesc();
    Optional<BookEntity> findByBookRegId(String bookRegId);
}
