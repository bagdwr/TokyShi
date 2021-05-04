package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_rolls")
public class Rolls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="roll_picture")
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredients> ingredients;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Integer price;
}
