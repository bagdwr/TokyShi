package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ingredientName")
    private String ingredientName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredients)) return false;
        Ingredients that = (Ingredients) o;
        return getId().equals(that.getId()) && getIngredientName().equals(that.getIngredientName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIngredientName());
    }
}
