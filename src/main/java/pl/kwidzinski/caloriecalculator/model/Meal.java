package pl.kwidzinski.caloriecalculator.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;

    @NotBlank(message = "Meal name cannot be empty")
    private String mealName;

    @NotNull(message = "Date cannot be empty")
    @PastOrPresent(message = "Date cannot be from future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Integer totalKcal;
    private BigDecimal totalProtein;
    private BigDecimal totalFat;
    private BigDecimal totalCarbs;
    private BigDecimal totalFiber;

    @OneToMany(mappedBy = "meal")
    private Set<Ingredient> ingredients;

    public Meal() {
    }

    public Long getId() {
        return id;
    }

     void setId(final Long id) {
        this.id = id;
    }

     public String getMealName() {
        return mealName;
    }

     public void setMealName(final String mealName) {
        this.mealName = mealName;
    }

    public LocalDate getDate() {
        return date;
    }

     public void setDate(final LocalDate date) {
        this.date = date;
    }

     public Integer getTotalKcal() {
        return totalKcal;
    }

     public void setTotalKcal(final Integer totalKcal) {
        this.totalKcal = totalKcal;
    }

     public BigDecimal getTotalProtein() {
        return totalProtein;
    }

     public void setTotalProtein(final BigDecimal totalProtein) {
        this.totalProtein = totalProtein;
    }

     public BigDecimal getTotalFat() {
        return totalFat;
    }

     public void setTotalFat(final BigDecimal totalFat) {
        this.totalFat = totalFat;
    }

     public BigDecimal getTotalCarbs() {
        return totalCarbs;
    }

     public void setTotalCarbs(final BigDecimal totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

     public BigDecimal getTotalFiber() {
        return totalFiber;
    }

     public void setTotalFiber(final BigDecimal totalFiber) {
        this.totalFiber = totalFiber;
    }

     public Set<Ingredient> getIngredients() {
        return ingredients;
    }

     public void setIngredients(final Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
