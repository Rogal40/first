package com.Lab1.Lab1Namazbay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    // Метод обрабатывает GET запрос на URL "/greeting"
    @GetMapping("/greeting")
    public String greetingPage(
            @RequestParam(name = "name", required = false, defaultValue = "Guest") String name,
            Model model) {
        // Передаем параметр name на страницу
        model.addAttribute("name", name);
        // Возвращаем имя HTML-шаблона для отображения
        return "greeting";
    }
}
