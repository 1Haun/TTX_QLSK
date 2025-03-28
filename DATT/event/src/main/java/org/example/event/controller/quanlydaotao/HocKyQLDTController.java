package org.example.event.controller.quanlydaotao;

import org.example.event.entity.HocKy;
import org.example.event.repository.HocKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quanlydaotao")
public class HocKyQLDTController {
    @Autowired
    private HocKyRepository hocKyRepository;

    // Hiển thị danh sách học kỳ
    @GetMapping("/hocky-qldt")
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

        return "quanlydaotao/hocky-qldt";
    }
}
