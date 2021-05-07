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

import java.time.LocalDate;
import java.util.*;

@Service
public class MealService {

    private final IngredientRepo ingredientRepo;
    private final MealRepo mealRepo;
    private final DataParser dataParser;
    private final Set<Ingredient> tempList;

    @Autowired
    public MealService(final IngredientRepo ingredientRepo, final MealRepo mealRepo, final DataParser dataParser) {
        this.ingredientRepo = ingredientRepo;
        this.mealRepo = mealRepo;
        this.dataParser = dataParser;
        this.tempList = new HashSet<>();
    }

    public Set<Ingredient> getTempList() {
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

    public void update(Meal meal) {
        Optional<Meal> optionalMeal = mealRepo.findById(meal.getId());
        if (optionalMeal.isPresent()) {
            Meal mealToEdit = optionalMeal.get();
            Set<Ingredient> foundIngredients = mealToEdit.getIngredients();

            mealToEdit.setMealName(meal.getMealName());
            mealToEdit.setDate(meal.getDate());
            mealToEdit.setTotalKcal(countTotalKcal(foundIngredients));
            mealToEdit.setTotalProtein(countTotalProtein(foundIngredients));
            mealToEdit.setTotalFat(countTotalFat(foundIngredients));
            mealToEdit.setTotalCarbs(countTotalCarbs(foundIngredients));
            mealToEdit.setTotalFiber(countTotalFiber(foundIngredients));
            mealRepo.save(mealToEdit);
        }
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

    public List<Meal>findByDate(LocalDate from, LocalDate to) {
        return mealRepo.findAllByDateBetweenOrderByDateAsc(from, to);
    }


    public Integer countTotalKcal(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getCalories)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Double countTotalProtein(Set<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getProtein)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);
    }

    public Double countTotalFat(Set<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getFat)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);
    }

    public Double countTotalCarbs(Set<Ingredient> ingredients) {
        return dataParser.roundDouble(ingredients.stream()
                .map(Ingredient::getCarbs)
                .mapToDouble(Double::doubleValue)
                .sum(), 2);

    }

    public Double countTotalFiber(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getFiber)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}

