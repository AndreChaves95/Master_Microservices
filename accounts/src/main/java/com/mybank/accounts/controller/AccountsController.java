package com.mybank.accounts.controller;

import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.dto.ErrorResponseDto;
import com.mybank.accounts.dto.ResponseDto;
import com.mybank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REST API - CRUD Operations for Accounts", description = "Accounts microservice REST API documentation") // Tag for OpenAPI documentation
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE) // REST API will support responses compatible with JSON type
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService accountsService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @Operation(summary = "Create a new customer account",
               description = "This endpoint creates a new customer account. The request body must contain the customer's details.")
    @ApiResponse(responseCode = "201",
                 description = "HTTP Status CREATED - Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomerAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createCustomerAccount(customerDto); // Call the service to create an account
        return ResponseEntity
                .status(HttpStatus.CREATED)     // Status will be inside header
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));    // ResponseDto will be inside body
    }


    @Operation(summary = "Get Account Details",
               description = "REST API to fetch Customer & Account details based on mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                         description = "HTTP Status OK - Account details fetched successfully"),
            @ApiResponse(responseCode = "500",
                         description = "HTTP Status Internal Server Error - Unable to fetch account details",
                         content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/accounts")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountsService.getAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }


    @Operation(summary = "Update Account Details",
               description = "REST API to update Customer & Account details based on account number")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                         description = "HTTP Status OK - Account details updated successfully"),
            @ApiResponse(responseCode = "417",
                         description = "Expectation Failed - The server could not retrieve the expected response"),
            @ApiResponse(responseCode = "500",
                         description = "HTTP Status Internal Server Error - Unable to update account details",
                         content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateCustomerAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(summary = "Delete Account & Customer Details REST API",
               description = "REST API to delete Customer &  Account details based on a mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                         description = "HTTP Status OK - Account details deleted successfully"),
            @ApiResponse(responseCode = "500",
                         description = "HTTP Status Internal Server Error - Unable to delete account details",
                         content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
        boolean isDeleted = accountsService.deleteCustomerAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }
}
