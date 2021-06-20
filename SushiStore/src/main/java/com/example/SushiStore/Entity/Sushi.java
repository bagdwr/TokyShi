package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "t_sushi")
@AllArgsConstructor
@NoArgsConstructor
public class Sushi{
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

    public String getIngredientsString(){
        List<Ingredients> ingredients= this.ingredients;
        String s="";
        for (int i=0; i<ingredients.size(); i++){
            s=s+ingredients.get(i).getIngredientName();
            if (i==ingredients.size()-1){
                continue;
            }
            s=s+", ";
        }
        return s;
    }
    public int getOverallPrice(int k){
        return k*this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sushi)) return false;
        Sushi sushi = (Sushi) o;
        return getId().equals(sushi.getId()) && getName().equals(sushi.getName()) && getPrice().equals(sushi.getPrice()) && getIngredients().equals(sushi.getIngredients()) && getSushi_picture().equals(sushi.getSushi_picture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getIngredients(), getSushi_picture());
    }
}
