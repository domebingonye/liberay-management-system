package com.maids.cc.library_management_system.domain;

import lombok.Data;

@Data
public class Patron {
    private Long id;
    private String name;
    private String contact;
    private String information;
    private String patronRegId;
}
