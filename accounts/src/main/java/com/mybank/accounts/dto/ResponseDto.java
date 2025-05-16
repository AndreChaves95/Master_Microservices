package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(name = "Response",
        description = "Schema to hold successful response information")
@AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String statusMessage;
}
