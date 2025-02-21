package org.example.event.controller.bandaotao;

import org.example.event.entity.HocKy;
import org.example.event.repository.HocKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HocKyController {
    @Autowired
    private HocKyRepository hocKyRepository;

    // Hiển thị danh sách học kỳ
    @GetMapping("/bandaotao/hocky")
    public String getHocKyPage(Model model) {
        List<HocKy> listHocKy = hocKyRepository.findAll();
        model.addAttribute("listHocKy", listHocKy);
        return "bandaotao/hocky";
    }

    // Xử lý thêm học kỳ mới
    @PostMapping("/bandaotao/hocky/add")
    public String addHocKy(@RequestParam String name, @RequestParam String startDate,
                           @RequestParam String endDate, @RequestParam String status) {
        HocKy hocKy = new HocKy();
        hocKy.setName(name);
        hocKy.setStartDate(LocalDate.parse(startDate));
        hocKy.setEndDate(LocalDate.parse(endDate));
        hocKy.setStatus(status);

        hocKyRepository.save(hocKy);
        return "redirect:/bandaotao/hocky";
    }
}
