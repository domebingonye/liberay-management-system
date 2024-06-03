package com.maids.cc.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties(
        value = {"createdOn", "updatedOn", "createdBy", "updatedBy"},
        allowGetters = true
)
@Getter
@Setter
public abstract class AuditModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "CREATED_ON", updatable = false)
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @LastModifiedDate
    private LocalDateTime updatedOn;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Getter
    @Setter
    @EntityListeners({AuditingEntityListener.class})
    @JsonIgnoreProperties(
            value = {"authOn", "authBy"},
            allowGetters = true
    )
    public static abstract class AuthModelWithAuth extends AuditModel {
        @Column(name = "AUTH_ON", updatable = false)
        @CreatedDate
        private LocalDateTime authOn;

        @CreatedBy
        @Column(name = "AUTH_BY")
        private String authBy;
    }
}
