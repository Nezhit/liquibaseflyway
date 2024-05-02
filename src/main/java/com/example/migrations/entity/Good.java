package com.example.migrations.entity;

import com.example.migrations.dto.GoodDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Type", nullable = false)
    private Type type;

    @Column(name = "Title", nullable = false, length = 15)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    private Producer producer;
    public Good(GoodDto goodDto){
        this.title = goodDto.getTitle();
        this.type=goodDto.getType();
        this.producer=goodDto.getProducer();
    }
}
