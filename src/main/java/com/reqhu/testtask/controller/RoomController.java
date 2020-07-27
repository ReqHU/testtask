package com.reqhu.testtask.controller;

import com.reqhu.testtask.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository repository;

    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("rooms", repository.findAll());
        return "rooms";
    }

    @PostMapping("/add")
    public String add(String type) {
        if (type.isBlank()) {
            throw new IllegalArgumentException("Тип помещения не должен быть пустым.");
        }
        repository.nativeSqlSave(type);
        return "redirect:/rooms";
    }

}
