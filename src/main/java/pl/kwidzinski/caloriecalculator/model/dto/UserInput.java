package pl.kwidzinski.caloriecalculator.model.dto;

import javax.validation.constraints.NotBlank;

public class UserInput {

    @NotBlank(message = "Ingredient name cannot be empty")
    private String ingredientName;
    @NotBlank(message = "Ingredient weight cannot be empty")
    private String weight;

    public String getIngredientName() {
        return ingredientName;
    }

    public String getWeight() {
        return weight;
    }
}
