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

    @Override
    public boolean updateCustomerAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteCustomerAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        // deleteByCustomerId requires @Transactional and @Modifying annotations in the repository because it modifies the database and its not a default method of JpaRepository
        accountsRepository.deleteByCustomerId(customer.getCustomerId()); // Delete account associated with the customer
        // deleteById is a default method of JpaRepository and does not require @Transactional and @Modifying annotations
        customerRepository.deleteById(customer.getCustomerId()); // Delete the customer
        return true;
    }
}
