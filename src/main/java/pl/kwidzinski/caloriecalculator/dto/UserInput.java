package pl.kwidzinski.caloriecalculator.dto;

import javax.validation.constraints.NotBlank;

public class UserInput {

    @NotBlank(message = "Field cannot be empty")
    private String name;
    @NotBlank(message = "Field cannot be empty")
    private String parameter;

    public UserInput() {
    }

    public String getName() {
        return name;
    }

    public String getParameter() {
        return parameter;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setParameter(final String parameter) {
        this.parameter = parameter;
    }
}
