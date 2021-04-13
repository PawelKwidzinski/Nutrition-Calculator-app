package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.repository.MealRepo;

import java.util.List;

@Service
public class MealService {
    private MealRepo mealRepo;

    @Autowired
    public MealService(final MealRepo mealRepo) {
        this.mealRepo = mealRepo;
    }

    public List<Meal> findAll() {
        return mealRepo.findAll();
    }

    public void saveMeal(Meal meal) {
        mealRepo.save(meal);
    }
}

