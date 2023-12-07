package com.haakimi.dormInspection.mybatis;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 * @className MybatisUtils
 * @description TOO
 * @Author cfx
 * @DATE 2020/3/19 20:48
 * @VERSION 1.0
 **/
public class MybatisUtils {
    public static Map<String,Object> UserData(Map<String,Object> userData, IPage iPage,
                                Integer pageNum,Integer pageSize){
        userData.put("count",iPage.getTotal());
        userData.put("pageNum",pageNum);
        userData.put("pageSize",pageSize);
        return userData;
    }
}
