package com.haakimi.dormInspection.handler;

import com.haakimi.dormInspection.config.Error;
import com.haakimi.dormInspection.enums.ResultStatus;
import com.haakimi.dormInspection.exception.SystemErrorException;
import com.haakimi.dormInspection.utils.ResultMapHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SystemErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error global(SystemErrorException s){
        Error error = new Error(ResultStatus.Fail.getCode(),s.getErrorData());
        return error;
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        LOGGER.info("---------------> 异常拦截 <---------------------");

        Map<String, Object> result = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            result = ResultMapHelper.result(ResultStatus.NullParams.getCode(),fieldError.getField() + fieldError.getDefaultMessage());
            break;
        }
        return result;
    }



}
