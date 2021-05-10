package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kwidzinski.caloriecalculator.model.Meal;
import pl.kwidzinski.caloriecalculator.service.MealService;

import java.time.LocalDate;
import java.util.List;
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
    public String findByDate(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to, Model model) {
        List<Meal> byDate = mealService.findByDate(LocalDate.parse(from), LocalDate.parse(to));
        if (byDate.size() == 0 || LocalDate.parse(from).isAfter(LocalDate.parse(to))) {
            model.addAttribute("notFoundInRange", "Could not find any meals within provided range.");
        }
        model.addAttribute("meals", byDate);
        return "meal-list";
    }
}
