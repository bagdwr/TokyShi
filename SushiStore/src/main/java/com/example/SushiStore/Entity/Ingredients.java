package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ingredientName")
    private String ingredientName;
}
