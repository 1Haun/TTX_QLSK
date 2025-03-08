package org.example.event.controller.truongbandaotao;

import org.example.event.repository.KeHoachSuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/truongbandaotao")
public class SuKienController {
    @Autowired
    private KeHoachSuKienRepository keHoachSuKienRepository;
    @GetMapping("/quanlysukien")
    public String quanlysukien(Model model) {
        model.addAttribute("keHoachList", keHoachSuKienRepository.findAll());
        return "truongbandaotao/quanlysukien";
    }
}
