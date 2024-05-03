package com.example.migrations.dto;

import com.example.migrations.entity.Good;
import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodRsDto {
    private Type type;
    private String title;
    private Producer producer;

    public GoodRsDto(Good good) {
        this.type = good.getType();
        this.title = good.getTitle();
        this.producer = good.getProducer();
    }
}
