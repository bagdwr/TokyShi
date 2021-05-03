package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "t_sushi")
@AllArgsConstructor
@NoArgsConstructor
public class Sushi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredients> ingredients;

    @Column(name = "sushi_picture")
    private String sushi_picture;
}
