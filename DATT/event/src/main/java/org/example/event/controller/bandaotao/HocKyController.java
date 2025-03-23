package org.example.event.controller.bandaotao;

import org.example.event.entity.HocKy;
import org.example.event.repository.HocKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getHocKyPage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size);

        Page<HocKy> hocKyPage;
        if (keyword != null && !keyword.trim().isEmpty()) {
            hocKyPage = hocKyRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        } else {
            hocKyPage = hocKyRepository.findAll(pageable);
        }

        model.addAttribute("hocKyPage", hocKyPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", hocKyPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "bandaotao/hocky";
    }


    // Xử lý thêm học kỳ mới
    @PostMapping("/bandaotao/hocky/add")
    public String addHocKy(@RequestParam String name,
                           @RequestParam String startDate,
                           @RequestParam String endDate,
                           @RequestParam String status) {
        HocKy hocKy = new HocKy();
        hocKy.setName(name);
        hocKy.setStartDate(LocalDate.parse(startDate));
        hocKy.setEndDate(LocalDate.parse(endDate));
        hocKy.setStatus(status);

        hocKyRepository.save(hocKy);
        return "redirect:/bandaotao/hocky";
    }

    // Xử lý cập nhật học kỳ
    @PostMapping("/bandaotao/hocky/update")
    public String updateHocKy(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String status) {
        HocKy hocKy = hocKyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid HocKy Id:" + id));

        hocKy.setName(name);
        hocKy.setStartDate(LocalDate.parse(startDate));
        hocKy.setEndDate(LocalDate.parse(endDate));
        hocKy.setStatus(status);

        hocKyRepository.save(hocKy);
        return "redirect:/bandaotao/hocky";
    }

}