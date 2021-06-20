package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "t_drinks")
@AllArgsConstructor
@NoArgsConstructor
public class Drinks{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "drink_picture")
    private String drink_picture;

    public int getOverallPrice(int k){
        return k*this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drinks drinks = (Drinks) o;
        return Objects.equals(id, drinks.id) && Objects.equals(name, drinks.name) && Objects.equals(price, drinks.price) && Objects.equals(drink_picture, drinks.drink_picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, drink_picture);
    }
}
