package com.haakimi.dormInspection.config;

import lombok.Data;


@Data
public class Error {
    private String status;
    private String msg;
    private Object data;

    public Error(String code, String message) {
        this.status = code;
        this.msg = message;
        this.data = null;
    }
}
