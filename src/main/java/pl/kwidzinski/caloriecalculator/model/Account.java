package pl.kwidzinski.caloriecalculator.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;


import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @Cascade(value = org.hibernate.annotations.CascadeType.DETACH)
    private Set<AccountRole> roles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Set<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private Set<Meal> meals;

    public Account() {
    }

    public boolean isAdmin() {
        return roles.stream()
                .map(AccountRole::getName)
                .anyMatch(s -> s.equals("ADMIN"));
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(final boolean locked) {
        this.locked = locked;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(final Set<AccountRole> roles) {
        this.roles = roles;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final Set<Meal> meals) {
        this.meals = meals;
    }
}
