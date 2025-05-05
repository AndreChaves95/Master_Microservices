package com.mybank.accounts.repository;

import java.util.Optional;

import com.mybank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository  // Spring annotation to indicate that this class is a repository and should be managed by the Spring container
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
// JpaRepository is a JPA specific extension of the Repository interface that adds support for JPA and receives two parameters: the entity type and the type of the primary key

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional // Spring annotation to indicate that the method should be executed within a transaction
    @Modifying // Spring annotation to indicate that the method modifies the database
    void deleteByCustomerId(Long customerId); // Method to delete an account by customer ID

}
