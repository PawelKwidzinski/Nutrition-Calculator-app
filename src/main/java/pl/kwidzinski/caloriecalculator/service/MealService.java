package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.exceptions.MealCannotDeleteException;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.repository.MealRepo;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private final MealRepo mealRepo;

    @Autowired
    public MealService(final MealRepo mealRepo) {
        this.mealRepo = mealRepo;
    }

    public Optional<Meal> getById(Long id) {
        return mealRepo.findById(id);
    }

    public List<Meal> findAll() {
        return mealRepo.findAll();
    }

    public void saveMeal(Meal meal) {
        mealRepo.save(meal);
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
}

