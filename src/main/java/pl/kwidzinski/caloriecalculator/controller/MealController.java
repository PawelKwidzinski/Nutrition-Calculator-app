package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.service.IngredientService;
import pl.kwidzinski.caloriecalculator.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/meals/")
public class MealController {
    private final MealService mealService;
    private final IngredientService ingredientService;

    @Autowired
    public MealController(final MealService mealService, final IngredientService ingredientService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public String getMeals(Model model, Principal principal) {
        model.addAttribute("meals", mealService.findAllOrderByDateDesc(principal.getName()));
        return "meal-list";
    }

    @GetMapping("/add")
    public String addMeal(Model model) {
        model.addAttribute("meal", new Meal());
        return "meal-form";
    }

    @PostMapping("/add")
    public String addMeal(@Validated Meal meal, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "meal-form";
        }
        mealService.saveMeal(meal, principal.getName());
        return "redirect:/meals/ingredients/" + meal.getId();
    }

    @GetMapping("/ingredients/{id}")
    public String addMealToIngredients(Model model, @PathVariable("id") Long mealId, Principal principal) {
        Optional<Meal> mealOptional = mealService.findById(mealId);
        if (mealOptional.isPresent()) {
            Meal meal = mealOptional.get();
            List<Ingredient> ingredients = ingredientService.findAll(principal.getName());
            model.addAttribute("meal", meal);
            model.addAttribute("ingredients", ingredients);
            return "meal-ingredient-form";
        }
        return "redirect:/meals/list";
    }

    @PostMapping("/addIngredient")
    public String addIngredientsToMeal(Long mealId, Long ingredientId, HttpServletRequest request) {
        mealService.addIngredientToMeal(mealId, ingredientId);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/ingredient/remove/{ingredientId}/{mealId}")
    public String removeIngredientFromMeal(
            @PathVariable("ingredientId") Long ingredientId,
            @PathVariable("mealId") Long mealId,
            HttpServletRequest request) {
        mealService.removeIngredientFromMeal(ingredientId, mealId);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/edit/{id}")
    public String editMeal(Model model, @PathVariable(name = "id") Long mealId) {
        Optional<Meal> optionalMeal = mealService.findById(mealId);
        if (optionalMeal.isPresent()) {
            model.addAttribute("meal", optionalMeal.get());
            model.addAttribute("ingredients", optionalMeal.get().getIngredients());
            return "meal-edit";
        }
        return "redirect:/meal/list";
    }

    @PostMapping("/edit")
    public String editMeal(@Validated Meal meal, BindingResult result) {
        if (result.hasErrors()) {
            return "meal-edit";
        }
        mealService.update(meal);
        return "redirect:/meals/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable(name = "id") Long mealId) {
        Optional<Meal> optionalMeal = mealService.findById(mealId);
        if (optionalMeal.isPresent()) {
            mealService.removeMeal(mealId);
        }
        return "redirect:/meals/list";
    }

    @PostMapping("/find/date")
    public String findByDate(Model model, @RequestParam(value = "from") String from, @RequestParam(value = "to") String to, Principal principal) {
        try {
            List<Meal> byDate = mealService.findByDate(principal.getName(), LocalDate.parse(from), LocalDate.parse(to));
            if (byDate.isEmpty() || LocalDate.parse(from).isAfter(LocalDate.parse(to))) {
                model.addAttribute("notFoundInRange", "Could not find any meals within provided range.");
            }
            model.addAttribute("meals", byDate);
        } catch (DateTimeException e) {
            model.addAttribute("notEmpty", "Date cannot be empty!");
        }
        return "meal-list";
    }
}
