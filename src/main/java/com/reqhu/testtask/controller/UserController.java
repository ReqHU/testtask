package com.reqhu.testtask.controller;

import com.reqhu.testtask.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", repository.findAll());
        return "users";
    }

    @PostMapping("/add")
    public String add(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя участника не должно быть пустым.");
        }
        repository.nativeSqlSave(name);
        return "redirect:/users";
    }

}
