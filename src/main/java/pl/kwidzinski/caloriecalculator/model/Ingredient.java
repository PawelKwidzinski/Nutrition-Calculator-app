package pl.kwidzinski.caloriecalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;


import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;
    private String foodId;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String imageUrl;
    @NotBlank(message = "Unit cannot be empty")
    private String unit;
    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 1, message = "Quantity should be more than 0")
    private Integer quantity;
    @NotNull(message = "Calories cannot be empty")
    @Min(value = 1, message = "Calories should be more than 0")
    private Integer calories;
    @NotNull(message = "Protein cannot be empty")
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

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    private Set<Meal> meals;

    @ManyToOne
    private Account user;

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

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final Set<Meal> meals) {
        this.meals = meals;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(final Account user) {
        this.user = user;
    }
}
