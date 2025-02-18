package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChu {
    @GetMapping("/bandaotao")
    public String home(Model model) {
        model.addAttribute("message", "Chào mừng đến với Spring Boot + Thymeleaf!");
        return "bandaotao/trangchu";
    }
}
