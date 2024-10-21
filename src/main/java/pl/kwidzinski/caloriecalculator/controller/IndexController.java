package pl.kwidzinski.caloriecalculator.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwidzinski.caloriecalculator.dto.UserRegistration;
import pl.kwidzinski.caloriecalculator.service.AccountService;


@Controller
@RequestMapping("/")
public class IndexController {

    private final AccountService accountService;

    @Autowired
    public IndexController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/ingredients/search";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register-form";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistration userInput, BindingResult result, Model model) {
        if (!userInput.arePasswordEqual()) {
            model.addAttribute("errorMessage", "Passwords do not match");
            return "register-form";
        }
        if (result.hasGlobalErrors()) {
            model.addAttribute("errorMessage", result.getFieldError().getDefaultMessage());
            return "register-form";
        }
        if (!accountService.register(userInput)) {
            model.addAttribute("errorMessage", "This username is already taken.");
            return "register-form";
        }
        return "redirect:/login";
    }
}
