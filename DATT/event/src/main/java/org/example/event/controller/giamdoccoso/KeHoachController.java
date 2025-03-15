package org.example.event.controller.giamdoccoso;

import org.example.event.repository.KeHoachSuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/giamdoccoso")
public class KeHoachController {
    @Autowired
    private KeHoachSuKienRepository keHoachSuKienRepository;
    @GetMapping("/quanlysukien")
    public String quanlysukien(Model model) {
        model.addAttribute("keHoachList", keHoachSuKienRepository.findAll());
        return "giamdoccoso/quanlysukien";
    }
}



