package com.yylc.loanmarket.exception;


import com.yylc.loanmarket.entity.JsonResult;

/**
 * Created by admin on 2017/3/19.
 */
public class MicroserviceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String description;

    public MicroserviceException(String message, String description) {
        super(message);
        this.message = message;
        this.description = description;
    }

    public JsonResult<String> getResult() {
        return new JsonResult<String>().setCode("1").setMessage(message).setData(description);
    }


}
