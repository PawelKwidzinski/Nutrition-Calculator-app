package pl.kwidzinski.caloriecalculator.dto;

import javax.validation.constraints.NotBlank;

public class UserInput {

    @NotBlank(message = "Ingredient name cannot be empty")
    private String ingredientName;
    @NotBlank(message = "Ingredient parameter cannot be empty")
    private String parameter;

    public UserInput() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getParameter() {
        return parameter;
    }

    public void setIngredientName(final String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setParameter(final String parameter) {
        this.parameter = parameter;
    }
}
