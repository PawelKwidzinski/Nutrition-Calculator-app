package pl.kwidzinski.caloriecalculator.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    private String unit;
    private Integer quantity;
    @NotNull(message = "Calories cannot be empty")
    @Digits(integer = 3, fraction = 0,  message = "numeric value out of bounds (<3 digits>.<2 digits> expected)")
    private Integer calories;
    @NotNull(message = "Calories cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2, message = "numeric value out of bounds (<3 digits>.<2 digits> expected)")
    private BigDecimal protein;
    @NotNull(message = "Fat cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2,  message = "numeric value out of bounds (<3 digits>.<2 digits> expected)")
    private BigDecimal fat;
    @NotNull(message = "Carbs cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2,  message = "numeric value out of bounds (<3 digits>.<2 digits> expected)")
    private BigDecimal carbs;
    @NotNull(message = "Fiber cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 2, fraction = 2,  message = "numeric value out of bounds (<3 digits>.<2 digits> expected)")
    private BigDecimal fiber;

    @ManyToOne
    private Meal meal;

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public String getFoodId() {
        return foodId;
    }

     public void setId(final Long id) {
        this.id = id;
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

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(final BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(final BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(final BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getFiber() {
        return fiber;
    }

    public void setFiber(final BigDecimal fiber) {
        this.fiber = fiber;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(final Meal meal) {
        this.meal = meal;
    }
}
