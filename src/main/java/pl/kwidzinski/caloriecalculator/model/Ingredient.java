package pl.kwidzinski.caloriecalculator.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;
    private String foodId;
    @NotBlank(message = "Invalid input")
    private String name;
    private String imageUrl;
    private String unit;
    private Integer quantity;
    @NotNull(message = "Invalid input")
    @Min(value = 1, message = "Calories should be more than 0")
    private Integer calories;
    @NotNull(message = "Calories cannot be empty")
    @DecimalMin(value = "0.0")
    private Double protein;
    @NotNull(message = "Fat cannot be empty")
    @DecimalMin(value = "0.0")
    private Double fat;
    @NotNull(message = "Carbs cannot be empty")
    @DecimalMin(value = "0.0")
    private Double carbs;
    @NotNull(message = "Fiber cannot be empty")
    @DecimalMin(value = "0.0")
    private Double fiber;

    @ManyToOne
    private Meal meal;

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(final String foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(final String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(final Integer calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(final Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(final Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(final Double carbs) {
        this.carbs = carbs;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(final Double fiber) {
        this.fiber = fiber;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(final Meal meal) {
        this.meal = meal;
    }
}
