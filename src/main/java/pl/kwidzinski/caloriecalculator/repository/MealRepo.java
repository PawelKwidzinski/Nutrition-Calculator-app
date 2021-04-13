package pl.kwidzinski.caloriecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.caloriecalculator.model.Meal;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
}
