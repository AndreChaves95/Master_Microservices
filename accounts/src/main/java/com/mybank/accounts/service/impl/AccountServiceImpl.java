package com.mybank.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.AccountsDto;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.entity.Accounts;
import com.mybank.accounts.entity.Customer;
import com.mybank.accounts.exception.CustomerAlreadyExistsException;
import com.mybank.accounts.exception.ResourceNotFoundException;
import com.mybank.accounts.mapper.AccountsMapper;
import com.mybank.accounts.mapper.CustomerMapper;
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
    public void createCustomerAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        Optional<Customer> customerEmail = customerRepository.findByEmail(customerDto.getEmail());
        if (customerMobileNumber.isPresent() || customerEmail.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with this mobile number or email!");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Admin");
        return newAccount;
    }

    @Override
    public CustomerDto getAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Long customerId = customer.getCustomerId();
        Accounts account = accountsRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customerId));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerDto;
    }
}
