package com.maids.cc.library_management_system.repository;

import com.maids.cc.library_management_system.model.BorrowingRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordsRepository extends JpaRepository<BorrowingRecordsEntity, Long> {
}
