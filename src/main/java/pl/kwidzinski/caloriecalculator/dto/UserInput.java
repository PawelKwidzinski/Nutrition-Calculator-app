package pl.kwidzinski.caloriecalculator.dto;

import javax.validation.constraints.NotBlank;

public class UserInput {

    @NotBlank(message = "Ingredient name cannot be empty")
    private String ingredientName;
    @NotBlank(message = "Ingredient weight cannot be empty")
    private String weight;

    public UserInput() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getWeight() {
        return weight;
    }

    public void setIngredientName(final String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setWeight(final String weight) {
        this.weight = weight;
    }
}
