package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;
import pl.kwidzinski.caloriecalculator.repository.MealRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepo ingredientRepo;
    private final MealRepo mealRepo;

    @Autowired
    public IngredientService(final IngredientRepo ingredientRepo, final MealRepo mealRepo) {
        this.ingredientRepo = ingredientRepo;
        this.mealRepo = mealRepo;
    }

    public List<Ingredient> findAll(){
        return ingredientRepo.findAll();
    }

    public Optional<Ingredient> findById(final Long id) {
      return ingredientRepo.findById(id);
    }

    public void saveIngredient(final Ingredient toSave) {
        ingredientRepo.save(toSave);
    }

    public void saveIngredientToMeal(final Ingredient ingredient, final Long mealId){
        if (mealRepo.existsById(mealId)){
            Meal meal = mealRepo.getOne(mealId);
            ingredient.setMeal(meal);

            ingredientRepo.save(ingredient);
        } else {
            throw new EntityNotFoundException("Meal not found");
        }
    }

    public void deleteIngredient(final Long id) {
        ingredientRepo.deleteById(id);
    }
}
