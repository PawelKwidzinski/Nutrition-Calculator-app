package pl.kwidzinski.caloriecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/ingredients/search";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }
}
