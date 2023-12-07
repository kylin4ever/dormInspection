package com.haakimi.dormInspection.exception;

import lombok.Data;

/**
 * @className SystemErrorException
 * @description TOO
 * @Author cfx
 * @DATE 2020/3/19 21:55
 * @VERSION 1.0
 **/
@Data
public class SystemErrorException extends RuntimeException{
    private String errorData;

    public SystemErrorException(String errorData) {
        this.errorData = errorData;
    }
}
