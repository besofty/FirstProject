package com.besofty.firstproject.exception;


import com.besofty.firstproject.common.dto.JsonResp;
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
    public JsonResp validExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return generateForBindException(result);
    }

    @ResponseBody
    @ExceptionHandler(value = MicroserviceException.class)
    public JsonResp clientExceptionHandler(MicroserviceException ex){
        log.error(ex.getMessage());
        return ex.getResult();
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResp clientErrorHandler(RuntimeException ex){
        log.error(ex.getCause().getMessage());
        return JsonResp.fa("服务运行异常！");
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseBody
    public JsonResp timeOutException(TimeoutException ex) {
        log.error(ex.getMessage());
        return JsonResp.fa("接口超时！");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public JsonResp processAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return JsonResp.fa("拒绝连接！");
    }


    private JsonResp generateForBindException(BindingResult result){
        List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldErrorVM(f.getObjectName(), f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());
        String errorMessage = fieldErrors.stream().findFirst().get().getMessage();
        return JsonResp.fa(errorMessage);
    }

}
