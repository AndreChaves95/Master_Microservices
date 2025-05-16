package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
        description = "Schema to hold Customer and Account information")
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the customer", example = "email@email.com")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must have 9 digits")
    @Schema(description = "Mobile Number of the customer", example = "999999999")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
