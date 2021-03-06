package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_rolls")
public class Rolls{
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
        s=s+" /"+this.amount+"шт";
        return s;
    }

    public int getOverallPrice(int k){
        return k*this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rolls)) return false;
        Rolls rolls = (Rolls) o;
        return getId().equals(rolls.getId()) && getName().equals(rolls.getName()) && getUrl().equals(rolls.getUrl()) && getIngredients().equals(rolls.getIngredients()) && getAmount().equals(rolls.getAmount()) && getPrice().equals(rolls.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUrl(), getIngredients(), getAmount(), getPrice());
    }
}
