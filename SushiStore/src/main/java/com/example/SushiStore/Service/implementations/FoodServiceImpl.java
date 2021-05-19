package com.example.SushiStore.Service.implementations;

import com.example.SushiStore.Entity.*;
import com.example.SushiStore.Repositories.*;
import com.example.SushiStore.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private DrinksRepository drinksRepository;

    @Autowired
    private SushiRepository sushiRepository;

    @Autowired
    private RollsRepository rollsRepository;

    @Autowired
    private SetsRepository setsRepository;

    //region ingredients
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

    //endregion

    //region drinks
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

    @Override
    public ArrayList<Drinks> getAllDrinksSortedByName() {
        ArrayList<Drinks>drinks=getAllDrinks();
        for (int i=0; i<drinks.size()-1; i++){
            for (int j=0; j<drinks.size()-i-1; j++){
                if (drinks.get(j).getName().compareTo(drinks.get(j+1).getName())>0){
                    Drinks temp=drinks.get(j);
                    drinks.set(j,drinks.get(j+1));
                    drinks.set(j+1,temp);
                }
            }
        }
        return drinks;
    }
    //endregion

    //region sushi
    @Override
    public ArrayList<Sushi> getAllSushi() {
        return sushiRepository.findAll();
    }

    @Override
    public Sushi getOneSushi(Long id) {
        return sushiRepository.getById(id);
    }

    @Override
    public Sushi createSushi(Sushi sushi) {
        return sushiRepository.save(sushi);
    }

    @Override
    public Sushi saveSushi(Sushi sushi) {
        return sushiRepository.save(sushi);
    }

    @Override
    public void deleteSushi(Sushi sushi) {
        sushiRepository.delete(sushi);
    }
    //endregion

    //region rolls
    @Override
    public ArrayList<Rolls> getAllRolls() {
        return rollsRepository.findAll();
    }

    @Override
    public Rolls getOneRolls(Long id) {
        return rollsRepository.getById(id);
    }

    @Override
    public Rolls createRolls(Rolls roll) {
        return rollsRepository.save(roll);
    }

    @Override
    public Rolls saveRolls(Rolls roll) {
        return rollsRepository.save(roll);
    }

    @Override
    public void deleteRoll(Rolls rolls) {
        rollsRepository.delete(rolls);
    }
    //endregion

    //region sets

    @Override
    public ArrayList<Sets> getAllSets() {
        return setsRepository.findAll();
    }

    @Override
    public Sets getOneSet(Long id) {
        return setsRepository.getById(id);
    }

    @Override
    public Sets createSet(Sets set) {
        return setsRepository.save(set);
    }

    @Override
    public Sets saveSet(Sets set) {
        return setsRepository.save(set);
    }

    @Override
    public void deleteSet(Sets set) {
       setsRepository.delete(set);
    }
    //endregion
}
