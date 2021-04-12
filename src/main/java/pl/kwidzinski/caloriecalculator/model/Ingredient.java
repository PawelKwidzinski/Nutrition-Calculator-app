package pl.kwidzinski.caloriecalculator.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;
    private String name;
    private Integer quantity;
    private Integer calories;
    private BigDecimal protein;
    private BigDecimal fat;
    private BigDecimal carbs;
    private BigDecimal fiber;

    @ManyToOne
    private Meal meal;

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

}
