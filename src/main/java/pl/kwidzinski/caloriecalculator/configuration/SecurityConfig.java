package pl.kwidzinski.caloriecalculator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import pl.kwidzinski.caloriecalculator.service.AuthenticateService;

@Configuration
public class SecurityConfig {

    private AuthenticateService authenticateService;

    @Autowired
    public SecurityConfig(final AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/ingredients/**", "/meals/**").authenticated()
                        .requestMatchers("/css/**","/images/**","/js/**","/webjars/**","/login/**","/logout",
                                "/register").permitAll())
                .formLogin(flc -> flc.loginPage("/login").usernameParameter("username").passwordParameter("password")
                        .defaultSuccessUrl("/", true));

        return http.build();
    }

}
