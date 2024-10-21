package pl.kwidzinski.caloriecalculator.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.model.Account;
import pl.kwidzinski.caloriecalculator.model.Ingredient;
import pl.kwidzinski.caloriecalculator.repository.AccountRepository;
import pl.kwidzinski.caloriecalculator.repository.IngredientRepo;

import java.util.*;

@Service
public class IngredientService {

    private final IngredientRepo ingredientRepo;
    private final AccountRepository accountRepository;

    @Autowired
    public IngredientService(final IngredientRepo ingredientRepo, final AccountRepository accountRepository) {
        this.ingredientRepo = ingredientRepo;
        this.accountRepository = accountRepository;
    }

    public List<Ingredient> findAllIngredientsByUsername(final String username) {
        Account userAccount = accountRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s account not found", username)));
        return new ArrayList<>(userAccount.getIngredients());
    }

    public Optional<Ingredient> findById(final Long id) {
        return ingredientRepo.findById(id);
    }

    public void saveIngredient(final Ingredient toSave, String username) {
        Account userAccount = accountRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s account not found", username)));
        toSave.setUser(userAccount);
        ingredientRepo.save(toSave);
    }

    public void deleteIngredientById(final Long id) {
        ingredientRepo.deleteById(id);
    }
}
