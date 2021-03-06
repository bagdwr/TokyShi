package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private Map<Drinks,Integer> drinks=new HashMap<>();
    private Map<Rolls,Integer> rolls=new HashMap<>();
    private Map<Sets,Integer>sets=new HashMap<>();
    private Map<Sushi,Integer>sushi=new HashMap<>();

    public int getOverallAmount(){
        int sum=0;
        for(Map.Entry<Drinks,Integer>d:drinks.entrySet()){
            sum=sum+d.getValue();
        }
        for(Map.Entry<Rolls,Integer>r:rolls.entrySet()){
            sum=sum+r.getValue();
        }
        for(Map.Entry<Sets,Integer>s:sets.entrySet()){
            sum=sum+s.getValue();
        }
        for(Map.Entry<Sushi,Integer>s:sushi.entrySet()){
            sum=sum+s.getValue();
        }
        return sum;
    }
    public int getOverallPrice(){
        int sum=0;
        for(Map.Entry<Drinks,Integer>d:drinks.entrySet()){
            sum=sum+d.getKey().getOverallPrice(d.getValue());
        }
        for(Map.Entry<Rolls,Integer>r:rolls.entrySet()){
            sum=sum+r.getKey().getOverallPrice(r.getValue());
        }
        for(Map.Entry<Sets,Integer>s:sets.entrySet()){
            sum=sum+s.getKey().getOverallPrice(s.getValue());
        }
        for(Map.Entry<Sushi,Integer>s:sushi.entrySet()){
            sum=sum+s.getKey().getOverallPrice(s.getValue());
        }
        return sum;
    }
    public void clearBasket(){
        drinks=new HashMap<>();
        rolls=new HashMap<>();
        sets=new HashMap<>();
        sushi=new HashMap<>();
    }

    public Boolean checkEmpty(){
        return (rolls.isEmpty() && sushi.isEmpty() && sets.isEmpty() && drinks.isEmpty());
    }
}
