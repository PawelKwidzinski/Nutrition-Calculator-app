package pl.kwidzinski.caloriecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kwidzinski.caloriecalculator.model.Account;
import pl.kwidzinski.caloriecalculator.model.AccountRole;
import pl.kwidzinski.caloriecalculator.repository.AccountRepository;

import java.util.Optional;

@Service
public class AuthenticateService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AuthenticateService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            final Account account = optionalAccount.get();

            final String[] roles = account.getRoles().stream()
                    .map(AccountRole::getName)
                    .toArray(String[]::new);

            return User.builder()
                    .accountLocked(account.isLocked())
                    .username(account.getUsername())
                    .password(account.getPassword())
                    .roles(roles)
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
