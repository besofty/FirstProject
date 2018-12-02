package com.besofty.firstproject.exception;


import com.besofty.firstproject.common.dto.JsonResp;

public class MicroserviceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    public MicroserviceException(String message) {
        super(message);
        this.message = message;
    }

    public JsonResp getResult() {
        return JsonResp.fa(message);
    }

}
