package com.maids.cc.library_management_system.service;

import com.maids.cc.library_management_system.domain.Book;
import com.maids.cc.library_management_system.domain.BorrowingRecords;
import com.maids.cc.library_management_system.domain.Patron;
import com.maids.cc.library_management_system.model.BorrowingRecordsEntity;
import com.maids.cc.library_management_system.repository.BorrowingRecordsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowingRecordsService {
    private final BorrowingRecordsRepository repository;
    private final PatronService patronService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BorrowingRecords save(BorrowingRecords borrowingRecords){
        BorrowingRecordsEntity borrowingRecordsEntity = modelMapper.map(borrowingRecords, BorrowingRecordsEntity.class);
        return modelMapper.map(repository.save(borrowingRecordsEntity), BorrowingRecords.class);
    }

    public BorrowingRecords createBorrowingRecords(String patronRegId, String bookRegId){
        Patron patron = patronService.getPatronByPatronRegId(patronRegId);
        if(ObjectUtils.isEmpty(patron)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found patron");
        Book book = bookService.getBookByBookRegId(bookRegId);
        if(ObjectUtils.isEmpty(book)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found book");
        return saveBorrowingRecords(patron.getId(), book.getId());
    }

    public BorrowingRecords saveBorrowingRecords(Long patronId, Long bookId){
        return save(BorrowingRecords.builder()
                .borrow(true)
                .bookId(bookId)
                .patronId(patronId)
                .returned(false).build());
    }

    public BorrowingRecords updateBorrowingRecordsById(Long id, BorrowingRecords borrowingRecords){
        BorrowingRecords res = findById(id);
        if(ObjectUtils.isEmpty(res)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for BorrowingRecords");
        res.setReturned(true);
        return save(res);
    }

    public BorrowingRecords findById(Long id){
        return repository.findById(id).map(borrowingRecordsEntity -> modelMapper.map(borrowingRecordsEntity, BorrowingRecords.class)).orElse(null);
    }
}
