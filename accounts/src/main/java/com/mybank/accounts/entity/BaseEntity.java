package com.mybank.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass   // JPA annotation to indicate that this class is not an entity but should have its attributes inherited by entities that extend it
@EntityListeners(AuditingEntityListener.class) // JPA annotation to indicate that this class should be audited by the specified listener
@Getter
@Setter
@ToString
public class BaseEntity {

    @CreatedDate // JPA annotation to indicate that this field should be populated with the current date/time when the entity is created
    @Column(updatable = false)  // JPA annotation to indicate that this column should not be updated when the entity is updated
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)  // JPA annotation to indicate that this column should not be updated when the entity is updated
    private String createdBy;

    @LastModifiedDate // JPA annotation to indicate that this field should be populated with the current date/time when the entity is updated
    @Column(insertable = false) // JPA annotation to indicate that this column should not be inserted when the entity is created
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false) // JPA annotation to indicate that this column should not be inserted when the entity is created
    private String updatedBy;
}
