package com.example.migrations.dto;

import com.example.migrations.entity.Producer;
import com.example.migrations.entity.Type;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodDto {

    private Long id;

    private Type type;

    private String title;

    private Producer producer;
}
