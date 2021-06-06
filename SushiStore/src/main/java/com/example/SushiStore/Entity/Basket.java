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
}
