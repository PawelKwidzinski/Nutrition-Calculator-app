package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.api.remotedata.DataFetcher;
import pl.kwidzinski.caloriecalculator.dto.UserInput;
import pl.kwidzinski.caloriecalculator.service.IngredientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/ingredients/")
public class IngredientController {

    private final DataFetcher dataFetcher;
    private final IngredientService ingredientService;
    private List<Ingredient> ingredientsFromApi;

    @Autowired
    public IngredientController(final DataFetcher dataFetcher, final IngredientService ingredientService) {
        this.dataFetcher = dataFetcher;
        this.ingredientService = ingredientService;
        this.ingredientsFromApi = new ArrayList<>();
    }

    @GetMapping("/search")
    public String searchIngredients(Model model) {
        model.addAttribute("userInput", new UserInput());
        return "ingredient-form";
    }

    @PostMapping("/search")
    public String showSearchingIngredients(@Validated UserInput userInput, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ingredient-form";
        }
        try {
            int weight = Integer.parseInt(userInput.getWeight());
            if (weight <= 0) {
                model.addAttribute("error", "Weight must be greater than zero!");
                return "ingredient-form";
            }

            ingredientsFromApi = dataFetcher.fetchDataFromApi(userInput.getIngredientName(), weight);

            if (ingredientsFromApi.size() == 0) {
                model.addAttribute("error", "Searching ingredient not found!");
                return "ingredient-form";
            }

            model.addAttribute("foundIngredients", ingredientsFromApi);
            return "ingredient-list";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Weight must be a number!");
            return "ingredient-form";
        }
    }

    @GetMapping("/add/{id}")
    public String saveIngredientFromApi(@PathVariable String id) {
        Ingredient toSave = ingredientsFromApi.stream()
                .filter(element -> Objects.equals(element.getFoodId(), id))
                .findFirst().get();
        ingredientService.saveFromApi(toSave);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/list")
    public String findAllAddedIngredients(Model model) {
        List<Ingredient> allIngredients = ingredientService.findAll();
        model.addAttribute("foundIngredients", allIngredients);
        return "ingredient-saved-list";
    }
}
