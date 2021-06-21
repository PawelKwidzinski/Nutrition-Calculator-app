package pl.kwidzinski.caloriecalculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientRepo ingredientRepo;

    @InjectMocks
    private IngredientService ingredientService;

    private List<Ingredient> testIngredientList() {
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("Becon");
        final Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("Egg");
        return Arrays.asList(ingredient1, ingredient2);
    }

    @Test
    void should_return_all_ingredients() {
        // given
        List<Ingredient> testIngredients = testIngredientList();
        given(ingredientService.findAll()).willReturn(testIngredients);

        //when
        List<Ingredient>expectedIngredients = ingredientService.findAll();

        //then
        assertEquals(expectedIngredients, testIngredients);
        verify(ingredientRepo).findAll();
    }

    @Test
    void should_return_ingredient_by_id() {
        // given
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setName("Ham");

        //when
        when(ingredientRepo.findById(anyLong())).thenReturn(Optional.of(ingredient3));

        //then
        Optional<Ingredient> actual = ingredientService.findById(3L);
        Assertions.assertEquals(3L, actual.get().getId());
        verify(ingredientRepo).findById(ingredient3.getId());
    }

    @Test
    void should_add_given_Ingredient() {
        // given
        Ingredient ingredient4 = new Ingredient();
        ingredient4.setId(4L);
        ingredient4.setName("Cheese");

        // when
        when(ingredientRepo.save(any(Ingredient.class))).thenReturn(ingredient4);

        // then
        ingredientService.saveIngredient(ingredient4);
        verify(ingredientRepo).save(ingredient4);
    }
    @Test
    void should_delete_Ingredient() {
        // when
        ingredientService.deleteIngredientById(1L);

        //then
        verify(ingredientRepo, times(1)).deleteById(anyLong());
    }
}
