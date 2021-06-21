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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return "ingredient-search";
    }

    @PostMapping("/search")
    public String showSearchingIngredients(@Validated UserInput userInput, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ingredient-search";
        }
        try {
            int weight = Integer.parseInt(userInput.getParameter());
            if (weight <= 0) {
                model.addAttribute("error", "Weight must be greater than zero!");
                return "ingredient-search";
            }

            ingredientsFromApi = dataFetcher.fetchDataFromApi(userInput.getName(), weight);

            if (ingredientsFromApi.size() == 0) {
                model.addAttribute("error", "Searching ingredient not found!");
                return "ingredient-search";
            }

            model.addAttribute("foundIngredients", ingredientsFromApi);
            return "ingredient-search";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Weight must be a number!");
            return "ingredient-search";
        }
    }

    @GetMapping("/add/{foodId}")
    public String saveIngredientFromApi(@PathVariable String foodId) {
        Ingredient toSave = ingredientsFromApi.stream()
                .filter(element -> Objects.equals(element.getFoodId(), foodId))
                .findFirst().get();
        ingredientService.saveIngredient(toSave);
        return "redirect:/ingredients/search";
    }

    @GetMapping("/add")
    public String addIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient-form";
    }

    @PostMapping("/add")
    public String addIngredient(@Validated Ingredient ingredient, BindingResult result) {
        if (result.hasErrors()) {
            return "ingredient-form";
        }
        ingredientService.saveIngredient(ingredient);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/list")
    public String findAllAddedIngredients(Model model) {
        List<Ingredient> allIngredients = ingredientService.findAll();
        model.addAttribute("foundIngredients", allIngredients);
        return "ingredient-saved-list";
    }

    @GetMapping("/edit/{id}")
    public String editIngredient(Model model, @PathVariable(name = "id") Long ingredientId) {
        Optional<Ingredient> optIngredient = ingredientService.findById(ingredientId);
        if (optIngredient.isPresent()) {
            model.addAttribute("ingredient", optIngredient.get());
            return "ingredient-form";
        }
        return "redirect:/ingredients/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        String referer = request.getHeader("referer");
        ingredientService.deleteIngredientById(id);
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/ingredients/list";
    }
}
