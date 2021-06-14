package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "sets")
@NoArgsConstructor
@AllArgsConstructor
public class Sets{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "set_picture")
    private String set_picture;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Sushi>sushiList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rolls>rollsList;

    public int getOverallPrice(int k){
        return k*this.price;
    }
    public String getItems(){
        String s="";
        if (!this.sushiList.isEmpty() && !this.rollsList.isEmpty()){
            s="Суши: ";
            for (int i=0; i<sushiList.size(); i++){
                s=s+sushiList.get(i).getName();
                if (i==sushiList.size()-1){
                    continue;
                }
                s=s+", ";
            }
            s=s+"\nРоллы: ";
            for (int i=0; i<rollsList.size(); i++){
                s=s+rollsList.get(i).getName();
                if (i==rollsList.size()-1){
                    continue;
                }
                s=s+", ";
            }
        }
        if (!this.sushiList.isEmpty() && this.rollsList.isEmpty()){
            s="Суши: ";
            for (int i=0; i<sushiList.size(); i++){
                s=s+sushiList.get(i).getName();
                if (i==sushiList.size()-1){
                    continue;
                }
                s=s+", ";
            }
        }
        if (this.sushiList.isEmpty() && !this.rollsList.isEmpty()){
            s="Роллы: ";
            for (int i=0; i<rollsList.size(); i++){
                s=s+rollsList.get(i).getName();
                if (i==rollsList.size()-1){
                    continue;
                }
                s=s+", ";
            }
        }
        s=s+" /"+amount+"шт";
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sets sets = (Sets) o;
        return Objects.equals(id, sets.id) && Objects.equals(name, sets.name) && Objects.equals(set_picture, sets.set_picture) && Objects.equals(amount, sets.amount) && Objects.equals(price, sets.price) && Objects.equals(sushiList, sets.sushiList) && Objects.equals(rollsList, sets.rollsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, set_picture, amount, price, sushiList, rollsList);
    }
}
