package com.example.SushiStore.Service;

import com.example.SushiStore.Entity.Drinks;
import com.example.SushiStore.Entity.Ingredients;
import com.example.SushiStore.Entity.Sushi;
import com.example.SushiStore.Entity.Users;

import java.util.ArrayList;

public interface FoodService {
    //For ingredients
    ArrayList<Ingredients>getAllIngredients();
    Ingredients getOneIngredientById(Long id);
    Ingredients createIngredient(Ingredients ingredient);
    Ingredients saveIngredient(Ingredients Ingredient);
    void deleteIngredient(Ingredients ingredient);


    //for drinks
    ArrayList<Drinks>getAllDrinks();
    Drinks getOneDrink(Long id);
    Drinks createDrink(Drinks drink);
    Drinks saveDrink(Drinks drink);
    void deleteDrink(Drinks drink);

    //for sushi
    ArrayList<Sushi>getAllSushi();
    Sushi getOneSushi(Long id);
    Sushi createSushi(Sushi sushi);
    Sushi saveSushi(Sushi sushi);
    void deleteSushi(Sushi sushi);
}
