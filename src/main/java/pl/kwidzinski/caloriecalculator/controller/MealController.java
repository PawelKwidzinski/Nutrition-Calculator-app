package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.service.MealService;


@Controller
@RequestMapping(path = "/meals/")
public class MealController {
    private final MealService mealService;

    @Autowired
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
        model.addAttribute("meal", new Meal());
        return "meal-form";
    }

    @PostMapping("/add")
    public String addMeal(Meal meal){
        mealService.saveMeal(meal);
        return "redirect:/meals/list";
    }
}
