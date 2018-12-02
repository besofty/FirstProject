package com.besofty.firstproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MicroserviceClientException extends MicroserviceException {

    public MicroserviceClientException(String message) {
        super(message);
    }

}
