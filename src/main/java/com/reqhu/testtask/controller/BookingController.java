package com.reqhu.testtask.controller;

import com.reqhu.testtask.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("bookings", service.findAll());
        return "bookings";
    }

    @PostMapping("/add")
    public String add(String name, String description, String startDateTime, String endDateTime, Integer userId, Integer roomId) {
        service.save(
                name, description, LocalDateTime.parse(startDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse(endDateTime, DateTimeFormatter.ISO_DATE_TIME), userId, roomId
        );
        return "redirect:/bookings";
    }

    @PostMapping("/delete")
    public String delete(int id) {
        service.deleteById(id);
        return "redirect:/bookings";
    }

}
