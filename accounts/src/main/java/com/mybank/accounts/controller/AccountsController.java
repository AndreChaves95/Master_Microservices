package com.mybank.accounts.controller;

import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.dto.ResponseDto;
import com.mybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE) // REST API will support responses compatible with JSON type
@AllArgsConstructor
public class AccountsController {

    private IAccountsService accountsService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomerAccount(@RequestBody CustomerDto customerDto) {
        accountsService.createCustomerAccount(customerDto); // Call the service to create an account
        return ResponseEntity
                .status(HttpStatus.CREATED)     // Status will be inside header
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));    // ResponseDto will be inside body
    }
}
