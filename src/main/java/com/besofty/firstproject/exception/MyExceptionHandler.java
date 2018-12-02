package com.yylc.loanmarket.exception;


import com.yylc.loanmarket.entity.JsonResult;
import feign.FeignException;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger log = Logger.getLogger(MyExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult validExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return generateForBindException(result);
    }

    @ResponseBody
    @ExceptionHandler(value = MicroserviceException.class)
    public JsonResult clientExceptionHandler(MicroserviceException ex){
        log.error(ex.getMessage());
        return ex.getResult();
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResult<String> clientErrorHandler(RuntimeException ex){
        log.error(ex.getCause().getMessage());
        return new JsonResult<String>()
                .setCode("1")
                .setData(ex.getMessage())
                .setMessage("服务运行异常！");
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseBody
    public JsonResult<String> timeOutException(TimeoutException ex) {
        log.error(ex.getMessage());
        return new JsonResult<String>()
                .setCode("1")
                .setData(ex.getMessage())
                .setMessage("接口超时！");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public JsonResult<String> processAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return new JsonResult<String>()
                .setCode("1")
                .setData(ex.getMessage())
                .setMessage("拒绝连接！");
    }

    @ResponseBody
    @ExceptionHandler(FeignException.class)
    public JsonResult processFeignException(FeignException ex) {
        log.error(ex.getMessage());
        return new JsonResult<String>()
                .setCode("1")
                .setData(ex.getMessage())
                .setMessage("内部服务异常！");
    }

    private JsonResult generateForBindException(BindingResult result){
        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());
        String errorMessage = fieldErrors.stream().findFirst().get().getMessage();
        return new JsonResult().setCode("1")
                .setMessage(errorMessage);
    }

}
