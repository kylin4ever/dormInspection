package com.haakimi.dormInspection.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 由于 时间戳 并不能直接被 fastJSON 转换为 LocalDateTime 类型，因此我们需要自定义一个序列化执行器
 * LocalDateTime 反序列化（将前端传递的 时间戳 转换为 LocalDateTime 类型）
 *
 * @author Chimm Huang
 * @date 2020/3/7
 */
public class LocalDateTimeDeserializer implements ObjectDeserializer {

    @Override
    @SuppressWarnings("unchecked")
    public LocalDateTime deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

        String timestampStr = parser.getLexer().numberString();

        if (timestampStr == null || "".equals(timestampStr)) {
            return null;
        }

        timestampStr = timestampStr.replaceAll("\"", "");

        long timestamp = Long.parseLong(timestampStr);
        if(timestamp == 0) {
            return null;
        }
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

