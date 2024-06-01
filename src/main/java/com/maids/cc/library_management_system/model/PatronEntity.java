package com.maids.cc.library_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Accessors(chain = true)
@Data
@NoArgsConstructor
@Table(name = "PATRON")
public class PatronEntity {
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "INFORMATION")
    private String information;

    @Column(name = "PATRON_REG_ID")
    private String patronRegId;
}
