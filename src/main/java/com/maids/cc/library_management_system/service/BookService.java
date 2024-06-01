package com.maids.cc.library_management_system.service;

import com.maids.cc.library_management_system.domain.Book;
import com.maids.cc.library_management_system.domain.notification.BaseResponse;
import com.maids.cc.library_management_system.enumerator.ResponseType;
import com.maids.cc.library_management_system.interfaces.Id;
import com.maids.cc.library_management_system.model.BookEntity;
import com.maids.cc.library_management_system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final ModelMapper modelMapper;

    public Book save(Book book){
        BookEntity bookEntity = modelMapper.map(book, BookEntity.class);
        return modelMapper.map(repository.save(bookEntity), Book.class);
    }

    public Book createBook(Book book){
        String bookRegId = getBookRegId();
        book.setBookRegId(bookRegId);
        return save(book);
    }

    public Book updateBook(Long id, Book book){
        Book res = findById(id);
        if(ObjectUtils.isEmpty(res)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for " + id);
        book.setId(id);
        return save(book);
    }

    public Book findById(Long id){
        return repository.findById(id).map(bookEntity -> modelMapper.map(bookEntity, Book.class)).orElse(null);
    }

    public BaseResponse deleteById(Long id){
        Book book = findById(id);
        if(ObjectUtils.isEmpty(book)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for " + id);
        repository.deleteById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage(ResponseType.DELETED_MESSAGE.getResponse());
        return baseResponse;
    }

    public List<Book> getAllBooks(){
        return repository.findAll().stream().map(bookEntity -> modelMapper.map(bookEntity, Book.class)).collect(Collectors.toList());
    }

    private String getBookRegId(){
        Id id = repository.findTopByOrderByIdDesc().orElse(null);
        int newIdNumber = (id == null) ? 1 : id.getId().intValue() + 1;
        return "BK" + newIdNumber;
    }

    public Book getBookByBookRegId(String bookRegId){
        return repository.findByBookRegId(bookRegId).map(bookEntity -> modelMapper.map(bookEntity, Book.class)).orElse(null);
    }
}
