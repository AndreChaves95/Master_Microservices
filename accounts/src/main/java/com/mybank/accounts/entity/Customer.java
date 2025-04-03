package com.mybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id             // JPA annotation to indicate that this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // JPA annotation to indicate that the primary key is generated automatically
    @Column(name="customer_id")
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;
}
