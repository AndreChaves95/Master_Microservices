package com.mybank.accounts.service.impl;

import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.repository.AccountsRepository;
import com.mybank.accounts.repository.CustomerRepository;
import com.mybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
