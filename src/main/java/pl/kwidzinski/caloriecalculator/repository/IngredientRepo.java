package pl.kwidzinski.caloriecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.caloriecalculator.model.Ingredient;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
}
