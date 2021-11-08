package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.dto.UserRegistration;
import pl.kwidzinski.caloriecalculator.model.Account;
import pl.kwidzinski.caloriecalculator.model.AccountRole;
import pl.kwidzinski.caloriecalculator.repository.AccountRepository;
import pl.kwidzinski.caloriecalculator.repository.AccountRoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.users.roles:USER}")
    private String[] defaultUserRegisterRoles;

    @Autowired
    public AccountService(final AccountRepository accountRepository, final AccountRoleRepository accountRoleRepository, final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }


    public boolean register(final UserRegistration userInput) {
        if (accountRepository.existsByUsername(userInput.getUsername())) {
            return false;
        }
        Account newAccount = new Account();
        newAccount.setUsername(userInput.getUsername());
        newAccount.setPassword(passwordEncoder.encode(userInput.getPassword()));
        newAccount.setRoles(findRolesByName(defaultUserRegisterRoles));

        accountRepository.save(newAccount);
        return true;
    }

    private Set<AccountRole> findRolesByName(final String...roles) {
        Set<AccountRole> accountRoles = new HashSet<>();
        Arrays.stream(roles).forEach(role -> accountRoleRepository.findByName(role)
                .ifPresent(accountRoles::add));
        return accountRoles;
    }
}
