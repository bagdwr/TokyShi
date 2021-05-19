package com.example.SushiStore.Service;

import com.example.SushiStore.Entity.*;

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
    ArrayList<Drinks>getAllDrinksSortedByName();
    Drinks getOneDrink(Long id);
    Drinks createDrink(Drinks drink);
    Drinks saveDrink(Drinks drink);
    void deleteDrink(Drinks drink);

    //for sushi
    ArrayList<Sushi>getAllSushi();
    ArrayList<Sushi>getAllSushiSortedByPrice();
    Sushi getOneSushi(Long id);
    Sushi createSushi(Sushi sushi);
    Sushi saveSushi(Sushi sushi);
    void deleteSushi(Sushi sushi);

    //for rolls
    ArrayList<Rolls>getAllRolls();
    ArrayList<Rolls>getAllRollsSortedByPrice();
    Rolls getOneRolls(Long id);
    Rolls createRolls(Rolls roll);
    Rolls saveRolls(Rolls roll);
    void deleteRoll(Rolls rolls);

    //for sets
    ArrayList<Sets> getAllSets();
    ArrayList<Sets>getAllSetsSortedByPrice();
    Sets getOneSet(Long id);
    Sets createSet(Sets set);
    Sets saveSet(Sets set);
    void deleteSet(Sets set);
}
