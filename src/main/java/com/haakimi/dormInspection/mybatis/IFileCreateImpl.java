package com.haakimi.dormInspection.mybatis;

import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.FileType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @className IFileCreateImpl
 * @description TOO
 * @Author cfx
 * @DATE 2020/3/19 23:14
 * @VERSION 1.0
 **/
public class IFileCreateImpl implements IFileCreate {
    @Override
    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
        String name = fileType.name();
        System.out.println(name);
        System.out.println(filePath);
        //控制Controller，service,serviceImpl 有了就不覆盖
        Map<String,Integer> ingoreDir = new HashMap<>();
        ingoreDir.put("CONTROLLER", 1);
        ingoreDir.put("SERVICE", 1);
        ingoreDir.put("SERVICE_IMPL", 1);
        ingoreDir.put("MAPPER",1);
        ingoreDir.put("OTHER",1);
        if(ingoreDir.containsKey(name)){
            return checkDirExist(filePath);
        }
        //控制mapper和xml不覆盖
        return true;
    }

    public boolean checkDirExist(String filePath){
        File file = new File(filePath);
        return !file.exists();
    }

}
