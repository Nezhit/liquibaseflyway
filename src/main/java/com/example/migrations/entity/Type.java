package com.example.migrations.entity;

import com.example.migrations.dto.TypeDto;
import com.example.migrations.entity.enums.ETypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Title")
    private ETypes Title;

    public Type(TypeDto typeDto) {
        this.Title = typeDto.getTitle();
    }
}
