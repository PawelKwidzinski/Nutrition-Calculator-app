package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kwidzinski.caloriecalculator.exceptions.MealCannotDeleteException;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;
import pl.kwidzinski.caloriecalculator.repository.MealRepo;
import pl.kwidzinski.caloriecalculator.util.DataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final IngredientRepo ingredientRepo;
    private final MealRepo mealRepo;
    private final DataParser dataParser;
    private final List<Ingredient> tempList;

    @Autowired
    public MealService(final IngredientRepo ingredientRepo, final MealRepo mealRepo, final DataParser dataParser) {
        this.ingredientRepo = ingredientRepo;
        this.mealRepo = mealRepo;
        this.dataParser = dataParser;
        this.tempList = new ArrayList<>();
    }

    public List<Ingredient> getTempList() {
        return tempList;
    }

    public Optional<Meal> findById(Long id) {
        return mealRepo.findById(id);
    }

    public List<Meal> findAll() {
        return mealRepo.findAll();
    }

    @Transactional
    public void saveMeal(Meal toSave) {
        toSave.setTotalKcal(countTotalKcal(tempList));
        toSave.setTotalProtein(countTotalProtein(tempList));
        toSave.setTotalFat(countTotalFat(tempList));
        toSave.setTotalCarbs(countTotalCarbs(tempList));
        toSave.setTotalFiber(countTotalFiber(tempList));

        Meal savedMeal = mealRepo.save(toSave);
        tempList.forEach(ingredient -> ingredient.setMeal(savedMeal));
        ingredientRepo.saveAll(tempList);
        tempList.clear();
    }

    public void saveToTempList(Ingredient ingredient) {
        tempList.add(ingredient);
    }

    public void deleteFromTempList(Ingredient ingredient) {
        tempList.remove(ingredient);
    }

    public void removeMeal(Long id) {
        if (mealRepo.existsById(id)) {
            Meal mealToDelete = mealRepo.getOne(id);
            if (mealToDelete.getIngredients().size() == 0) {
                mealRepo.deleteById(id);
            } else {
                throw new MealCannotDeleteException(id);
            }
        }
    }

    public Integer countTotalKcal(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getCalories)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Double countTotalProtein(List<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getProtein)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);
    }

    public Double countTotalFat(List<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getFat)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);
    }

    public Double countTotalCarbs(List<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getCarbs)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);

    }

    public Double countTotalFiber(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getCarbs)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}

