package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;

import java.util.*;

@Service
public class IngredientService {

    private final IngredientRepo ingredientRepo;

    @Autowired
    public IngredientService(final IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public List<Ingredient> findAll() {
        return ingredientRepo.findAll();
    }

    public Optional<Ingredient> findById(final Long id) {
        return ingredientRepo.findById(id);
    }

    public void saveIngredient(final Ingredient toSave) {
        ingredientRepo.save(toSave);
    }

    public void deleteIngredient(final Long id) {
        ingredientRepo.deleteById(id);
    }
}
