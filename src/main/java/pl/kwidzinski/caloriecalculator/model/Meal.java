package pl.kwidzinski.caloriecalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Double totalProtein;
    private Double totalFat;
    private Double totalCarbs;
    private Double totalFiber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @ManyToOne()
    private Account user;

    public Meal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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

    public Double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(final Double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public Double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(final Double totalFat) {
        this.totalFat = totalFat;
    }

    public Double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(final Double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Double getTotalFiber() {
        return totalFiber;
    }

    public void setTotalFiber(final Double totalFiber) {
        this.totalFiber = totalFiber;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(final Account user) {
        this.user = user;
    }
}
