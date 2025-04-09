package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "main";
    }

        @GetMapping("/admin/dashboard")
        public String adminDashboard () {
            return "redirect:/admin/panel";
        }

        @GetMapping("/customer/dashboard")
        public String customerDashboard () {
            return "redirect:/customer/panel";
    }
}
