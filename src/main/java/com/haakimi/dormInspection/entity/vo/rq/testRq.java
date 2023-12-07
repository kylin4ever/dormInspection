package com.haakimi.dormInspection.entity.vo.rq;

import com.alibaba.fastjson.annotation.JSONField;
import com.haakimi.dormInspection.config.LocalDateTimeDeserializer;
import com.haakimi.dormInspection.config.LocalDateTimeSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class testRq {
    @NotNull
    private Integer id;

    @JSONField(serializeUsing = LocalDateTimeSerializer.class, deserializeUsing = LocalDateTimeDeserializer.class)
    private LocalDateTime time;
}
