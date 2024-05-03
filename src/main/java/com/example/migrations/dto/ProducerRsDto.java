package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProducerRsDto {
    private String title;
    private String address;
    private String phone;

    public ProducerRsDto(Producer producer) {
        this.title = producer.getTitle();
        this.address = producer.getAddress();
        this.phone = producer.getPhone();
    }
}
