package pl.kwidzinski.caloriecalculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kwidzinski.caloriecalculator.model.Account;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.repository.AccountRepository;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientRepo ingredientRepo;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private IngredientService ingredientService;

    private Account testAccount;
    private Ingredient testIngredient;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setUsername("testUser");
        testAccount.setIngredients(new HashSet<>());

        testIngredient = new Ingredient();
        testIngredient.setId(1L);
        testIngredient.setName("Test Ingredient");
    }

    @Test
    void findAllIngredientsByUsername_WhenUserExists_ShouldReturnIngredients() {
        // given
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(testIngredient);
        testAccount.setIngredients(ingredients);
        // when
        when(accountRepository.findByUsername("testUser")).thenReturn(Optional.of(testAccount));
        List<Ingredient> result = ingredientService.findAllIngredientsByUsername("testUser");
        // then
        assertEquals(1, result.size());
        assertTrue(result.contains(testIngredient));
        verify(accountRepository).findByUsername("testUser");
    }

    @Test
    void findAllIngredientsByUsername_WhenUserDoesNotExist_ShouldThrowEntityNotFoundException() {
        // given + when
        when(accountRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
        // then
        assertThrows(EntityNotFoundException.class, () ->
                ingredientService.findAllIngredientsByUsername("nonExistentUser")
        );
        verify(accountRepository).findByUsername("nonExistentUser");
    }

    @Test
    void findById_WhenIngredientExists_ShouldReturnIngredient() {
        // given + when
        when(ingredientRepo.findById(1L)).thenReturn(Optional.of(testIngredient));
        Optional<Ingredient> result = ingredientService.findById(1L);
        // then
        assertTrue(result.isPresent());
        assertEquals(testIngredient, result.get());
        verify(ingredientRepo).findById(1L);
    }

    @Test
    void findById_WhenIngredientDoesNotExist_ShouldReturnEmptyOptional() {
        // given + when
        when(ingredientRepo.findById(2L)).thenReturn(Optional.empty());
        Optional<Ingredient> result = ingredientService.findById(2L);
        // then
        assertFalse(result.isPresent());
        verify(ingredientRepo).findById(2L);
    }

    @Test
    void saveIngredient_WhenUserExists_ShouldSaveIngredient() {
        // given + when
        when(accountRepository.findByUsername("testUser")).thenReturn(Optional.of(testAccount));
        ingredientService.saveIngredient(testIngredient, "testUser");
        // then
        verify(accountRepository).findByUsername("testUser");
        verify(ingredientRepo).save(testIngredient);
        assertEquals(testAccount, testIngredient.getUser());
    }

    @Test
    void saveIngredient_WhenUserDoesNotExist_ShouldThrowEntityNotFoundException() {
        // given + when
        when(accountRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());
        // then
        assertThrows(EntityNotFoundException.class, () ->
                ingredientService.saveIngredient(testIngredient, "nonExistentUser")
        );
        verify(accountRepository).findByUsername("nonExistentUser");
        verify(ingredientRepo, never()).save(any(Ingredient.class));
    }

    @Test
    void deleteIngredientById_ShouldCallRepositoryMethod() {
        // given + when
        ingredientService.deleteIngredientById(1L);
        // then
        verify(ingredientRepo).deleteById(1L);
    }

}