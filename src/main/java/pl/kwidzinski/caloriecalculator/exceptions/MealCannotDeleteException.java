package pl.kwidzinski.caloriecalculator.exceptions;

public class MealCannotDeleteException extends RuntimeException{

    public MealCannotDeleteException(Long id) {
        super(String.format("Meal with id: %d. Unable to remove meal with assigned ingredients to it", id));

    }
}
