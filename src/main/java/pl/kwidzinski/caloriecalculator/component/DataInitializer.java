package pl.kwidzinski.caloriecalculator.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kwidzinski.caloriecalculator.model.Account;
import pl.kwidzinski.caloriecalculator.model.AccountRole;
import pl.kwidzinski.caloriecalculator.repository.AccountRepository;
import pl.kwidzinski.caloriecalculator.repository.AccountRoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private AccountRepository accountRepository;
    private AccountRoleRepository accountRoleRepository;
    private PasswordEncoder passwordEncoder;

    public DataInitializer(final AccountRepository accountRepository, final AccountRoleRepository accountRoleRepository, final PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${default.roles}")
    private String[] defaultRoles;

    @Value("${default.admin.password}")
    private String defaultAdminPassword;


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        for (String role : defaultRoles) {
            addRole(role);
        }

        addUser("admin", defaultAdminPassword, "example@example.com", "ADMIN", "USER");
        addUser("user", "user", "example@example.com", "USER");
    }

    private void addUser(final String username, final String password, final String email, String...roles) {
        if (!accountRepository.existsByUsername(username)){
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));

            Set<AccountRole> userRoles = findRole(roles);
            account.setRoles(userRoles);

            accountRepository.save(account);
        }
    }

    private Set<AccountRole> findRole(final String[] roles) {
        Set<AccountRole> accountRoles = new HashSet<>();
        for (String role : roles) {
            accountRoleRepository.findByName(role).ifPresent(accountRoles::add);
        }
    return accountRoles;
    }

    private void addRole(final String roleToCreate) {
        if (!accountRoleRepository.existsByName(roleToCreate)) {
            AccountRole accountRole = new AccountRole();
            accountRole.setName(roleToCreate);

            accountRoleRepository.save(accountRole);
        }
    }
}
