package com.haakimi.dormInspection.enums;


public enum TestEnum implements ReturnEnums {
    Test("00","测试");

    private String desc;
    private String returnCode;

    TestEnum(String returnCode ,String desc) {
        this.desc = desc;
        this.returnCode = returnCode;
    }

    // 普通方法
    public String getCode() {
        return returnCode;
    }

    public String getMsg() {
        return desc;
    }
}
