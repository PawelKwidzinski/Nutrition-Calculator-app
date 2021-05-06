package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.service.MealService;

import java.util.Optional;

@Controller
@RequestMapping(path = "/meals/")
public class MealController {
    private final MealService mealService;

    public MealController(final MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/list")
    public String getMeals(Model model) {
        model.addAttribute("meals", mealService.findAll());
        return "meal-list";
    }

    @GetMapping("/add")
    public String addMeal(Model model) {
        model.addAttribute("ingredients", mealService.getTempList());
        model.addAttribute("meal", new Meal());
        return "meal-form";
    }

    @PostMapping("/add")
    public String addMeal(@Validated Meal meal, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            model.addAttribute("ingredients", mealService.getTempList());
            return "meal-form";
        }
        mealService.saveMeal(meal);
        return "redirect:/meals/list";
    }

    @GetMapping("/edit/{id}")
    public String editMeal(Model model, @PathVariable(name = "id") Long mealId) {
        Optional<Meal> optionalMeal = mealService.findById(mealId);
        if (optionalMeal.isPresent()) {
            model.addAttribute("meal", optionalMeal.get());
            model.addAttribute("ingredients", optionalMeal.get().getIngredients());
            return "meal-form";
        }
        return "redirect:/meal/list";
    }
}
