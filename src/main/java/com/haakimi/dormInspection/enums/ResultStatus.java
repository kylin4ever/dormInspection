package com.haakimi.dormInspection.enums;

/**
 * 通用枚举返回状态
 */

public enum ResultStatus implements ReturnEnums{

    //通用提示语
    Success("00","成功"),
    Fail("99","失败"),
    InvalidParams("10010","非法参数"),

    //业务相关状态
    NullParams("01","相关参数不能为空"),

    IncorrectOperation("100","业务操作有误");
    private String desc;
    private String returnCode;

    ResultStatus( String returnCode,String desc) {
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
