package com.yylc.loanmarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MicroserviceClientException extends MicroserviceException {

    public MicroserviceClientException(String message, String description) {
        super(message, description);
    }

}
