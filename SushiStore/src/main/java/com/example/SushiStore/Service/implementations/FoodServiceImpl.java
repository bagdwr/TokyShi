package com.example.SushiStore.Service.implementations;

import com.example.SushiStore.Entity.Drinks;
import com.example.SushiStore.Entity.Ingredients;
import com.example.SushiStore.Repositories.DrinksRepository;
import com.example.SushiStore.Repositories.IngredientsRepository;
import com.example.SushiStore.Service.FoodService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private DrinksRepository drinksRepository;

    @Override
    public ArrayList<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    @Override
    public Ingredients getOneIngredientById(Long id) {
        return ingredientsRepository.getById(id);
    }

    @Override
    public Ingredients createIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public Ingredients saveIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredients ingredient) {
        ingredientsRepository.delete(ingredient);
    }

    @Override
    public ArrayList<Drinks> getAllDrinks() {
        return drinksRepository.findAll();
    }

    @Override
    public Drinks getOneDrink(Long id) {
        return drinksRepository.getById(id);
    }

    @Override
    public Drinks createDrink(Drinks drink) {
        return drinksRepository.save(drink);
    }

    @Override
    public Drinks saveDrink(Drinks drink) {
        return drinksRepository.save(drink);
    }

    @Override
    public void deleteDrink(Drinks drink) {
        drinksRepository.delete(drink);
    }
}
