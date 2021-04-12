package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.caloriecalculator.api.model.IngredientApi;
import pl.kwidzinski.caloriecalculator.api.remotedata.DataFetcher;
import pl.kwidzinski.caloriecalculator.dto.UserInput;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class AppController {

    private final DataFetcher dataFetcher;
    private List<IngredientApi> ingredientListFromApi;

    @Autowired
    public AppController(final DataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
        this.ingredientListFromApi = new ArrayList<>();
    }

    @GetMapping("/search")
    public String index(Model model) {
        model.addAttribute("userInput", new UserInput());
        return "ingredient-main";
    }

    @PostMapping("/search")
    public String showSearchingIngredients(@Validated UserInput userInput, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ingredient-main";
        }
        try {
            int weight = Integer.parseInt(userInput.getWeight());
            if (weight <= 0) {
                model.addAttribute("error", "Weight must be greater than zero!");
                return "ingredient-main";
            }

            ingredientListFromApi = dataFetcher.fetchDataFromApi(userInput.getIngredientName(), weight);

            if (ingredientListFromApi.size() == 0) {
                model.addAttribute("error", "Searching ingredient not found!");
                return "ingredient-main";
            }

            model.addAttribute("foundIngredients", ingredientListFromApi);
            return "ingredient-main";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Weight must be a number!");
            return "ingredient-main";
        }
    }
}
