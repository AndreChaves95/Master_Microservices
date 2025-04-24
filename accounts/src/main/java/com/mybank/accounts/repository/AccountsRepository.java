package com.mybank.accounts.repository;

import java.util.Optional;

import com.mybank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Spring annotation to indicate that this class is a repository and should be managed by the Spring container
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
// JpaRepository is a JPA specific extension of the Repository interface that adds support for JPA and receives two parameters: the entity type and the type of the primary key

    Optional<Accounts> findByCustomerId(Long customerId);

}
