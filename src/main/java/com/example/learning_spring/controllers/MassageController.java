package com.example.learning_spring.controllers;

import com.example.learning_spring.Model.Massage;
import com.example.learning_spring.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MassageController {
    @Autowired
    private MassageRepository massageRepository;

    @GetMapping("/massage/add")
    public String add() {
        return "massage/add";
    }

    @PostMapping("/massage/add")
    public String add(@RequestParam String topic, @RequestParam String text, Model model) {
        Massage massage = new Massage(topic, text);
        massageRepository.save(massage);

        return "redirect:all";
    }

    @PostMapping("/massage/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Massage> byTopic;
        if (filter != null && !filter.isEmpty()) {
            byTopic = massageRepository.findByTopic(filter);
        } else {
            byTopic = massageRepository.findAll();
        }
        model.addAttribute("massages", byTopic);
        return "massage/all";
    }

    @GetMapping("/massage/all")
    public String all(Model model) {
        Iterable<Massage> massages = massageRepository.findAll();
        model.addAttribute("massages", massages);
        return "massage/all";
    }
}
