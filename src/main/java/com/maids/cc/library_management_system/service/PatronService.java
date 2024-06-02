package com.maids.cc.library_management_system.service;

import com.maids.cc.library_management_system.domain.Patron;
import com.maids.cc.library_management_system.domain.notification.BaseResponse;
import com.maids.cc.library_management_system.enumerator.ResponseType;
import com.maids.cc.library_management_system.interfaces.Id;
import com.maids.cc.library_management_system.model.PatronEntity;
import com.maids.cc.library_management_system.repository.PatronRepository;
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
public class PatronService {
    private final PatronRepository repository;
    private final ModelMapper modelMapper;

    public Patron save(Patron patron){
        PatronEntity patronEntity = modelMapper.map(patron, PatronEntity.class);
        return modelMapper.map(repository.save(patronEntity), Patron.class);
    }

    public Patron registerPatron(Patron patron){
        String patronRegId = getPatronRegId();
        patron.setPatronRegId(patronRegId);
        return save(patron);
    }

    public Patron updatePatron(Long id, Patron patron){
        Patron res = findById(id);
        if(ObjectUtils.isEmpty(res)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for " + id);
        patron.setId(id);
        return save(patron);
    }

    public Patron findById(Long id){
        return repository.findById(id).map(patronEntity -> modelMapper.map(patronEntity, Patron.class)).orElse(null);
    }

    public BaseResponse deleteById(Long id){
        Patron patron = findById(id);
        if(ObjectUtils.isEmpty(patron)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for " + id);
        repository.deleteById(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(HttpStatus.OK.value());
        baseResponse.setMessage(ResponseType.DELETED_MESSAGE.getResponse());
        return baseResponse;
    }

    public List<Patron> getAllPatron(){
        return repository.findAll().stream().map(patronEntity -> modelMapper.map(patronEntity, Patron.class)).collect(Collectors.toList());
    }

    private String getPatronRegId() {
        Id id = repository.findTopByOrderByIdDesc().orElse(null);
        int newIdNumber = (id == null) ? 1 : id.getId().intValue() + 1;
        return "PT" + newIdNumber;
    }

    public Patron getPatronByPatronRegId(String patronRegId){
        return repository.findByPatronRegId(patronRegId).map(patronEntity -> modelMapper.map(patronEntity, Patron.class)).orElse(null);
    }
}
