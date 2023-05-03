package com.example.monki.controllers;

import com.example.monki.models.Reserve;
import com.example.monki.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reserve")
@Controller
@RequiredArgsConstructor
public class ReserveController {
    public final EmailService emailService;

    @GetMapping("")
    public String reserve(Model model) {
        model.addAttribute("reserveForm", new Reserve());
        return "reserve";
    }

    @PostMapping("")
    public String reserve(@ModelAttribute("reserveForm") Reserve reserveForm) {
        emailService.sendMessage("monki.restaurant@yandex.ru", "Бронь от " + reserveForm.getPhone(), reserveForm.toString());
        return "redirect:reserve/success";
    }
}
