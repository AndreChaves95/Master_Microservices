package com.mybank.accounts.service;

import com.mybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

}
