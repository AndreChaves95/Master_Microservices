package com.mybank.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass   // JPA annotation to indicate that this class is not an entity but should have its attributes inherited by entities that extend it
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(updatable = false)  // JPA annotation to indicate that this column should not be updated when the entity is updated
    private LocalDateTime createdAt;

    @Column(updatable = false)  // JPA annotation to indicate that this column should not be updated when the entity is updated
    private String createdBy;

    @Column(insertable = false) // JPA annotation to indicate that this column should not be inserted when the entity is created
    private LocalDateTime updatedAt;

    @Column(insertable = false) // JPA annotation to indicate that this column should not be inserted when the entity is created
    private String updatedBy;
}
