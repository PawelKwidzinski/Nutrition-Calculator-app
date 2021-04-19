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
import pl.kwidzinski.caloriecalculator.service.MealService;

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
    private final MealService mealService;
    private List<Ingredient> ingredientsFromApi;

    @Autowired
    public IngredientController(final DataFetcher dataFetcher, final IngredientService ingredientService, final MealService mealService) {
        this.dataFetcher = dataFetcher;
        this.ingredientService = ingredientService;
        this.mealService = mealService;
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
            int weight = Integer.parseInt(userInput.getWeight());
            if (weight <= 0) {
                model.addAttribute("error", "Weight must be greater than zero!");
                return "ingredient-search";
            }

            ingredientsFromApi = dataFetcher.fetchDataFromApi(userInput.getIngredientName(), weight);

            if (ingredientsFromApi.size() == 0) {
                model.addAttribute("error", "Searching ingredient not found!");
                return "ingredient-search";
            }

            model.addAttribute("foundIngredients", ingredientsFromApi);
            return "ingredient-list";
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
        ingredientService.saveFromApi(toSave);
        return "redirect:/ingredients/search";
    }

    @GetMapping("/add")
    public String addIngredient(Model model) {
        model.addAttribute("meals", mealService.findAll());
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient-form";
    }

    @PostMapping("/add")
    public String addIngredient(Ingredient ingredient, Long mealId) {
        ingredient.setUnit("g");
        ingredientService.saveIngredient(ingredient, mealId);
        return "redirect:/ingredients/list";
    }

    @GetMapping("/list")
    public String findAllAddedIngredients(Model model) {
        List<Ingredient> allIngredients = ingredientService.findAll();
        model.addAttribute("foundIngredients", allIngredients);
        return "ingredient-saved-list";
    }

    @GetMapping("/edit/{id}")
    public String editIngredient(Model model, @PathVariable(name = "id") Long id) {
        Optional<Ingredient> optIngredient = ingredientService.findById(id);
        if (optIngredient.isPresent()) {
            model.addAttribute("meals", mealService.findAll());
            model.addAttribute("ingredient", optIngredient.get());
            return "ingredient-form";
        }
        return "ingredient-saved-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        String referer = request.getHeader("referer");
        ingredientService.deleteIngredient(id);
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/ingredients/list";
    }

    @GetMapping("/delete/{foodId}/{weight}")
    public String deleteIngredientFromTempList(@PathVariable String foodId, @PathVariable Integer weight) {
        Ingredient toDelete = ingredientService.getTempList().stream()
                .filter(ingredient -> Objects.equals(ingredient.getFoodId(), foodId))
                .filter(e -> Objects.equals(e.getQuantity(), weight))
                .findFirst().get();
        ingredientService.deleteFromTempList(toDelete);
        return "redirect:/meals/add";
    }
}
