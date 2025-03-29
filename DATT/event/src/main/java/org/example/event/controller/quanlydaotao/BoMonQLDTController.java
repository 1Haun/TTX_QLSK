package org.example.event.controller.quanlydaotao;

import org.example.event.entity.BoMon;
import org.example.event.repository.BoMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/quanlydaotao")
public class BoMonQLDTController {

    @Autowired
    private BoMonRepository boMonRepository;
    @GetMapping("/bomon")
    public String danhSachBoMon(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String keyword,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoMon> bomonPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            bomonPage = boMonRepository.findBoMonByCodeOrName(keyword, pageable);
            model.addAttribute("keyword", keyword); // Giữ lại keyword sau khi tìm kiếm
        } else {
            bomonPage = boMonRepository.findAll(pageable);
        }

        model.addAttribute("bomon", new BoMon());
        model.addAttribute("bomonPage", bomonPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bomonPage.getTotalPages());

        return "quanlydaotao/bomon-qldt";
    }


    @PostMapping("/bomon/add")
    public String addDepartment(@ModelAttribute BoMon boMon, RedirectAttributes redirectAttributes) {
        try{
            boMonRepository.save(boMon);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm bộ môn thành công thành công!");
        }catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("errorMessage", "Mã bộ môn đã tồn tại!");
        }
        return "redirect:/quanlydaotao/bomon";
    }
    @GetMapping("/bomon/edit/{id}")
    @ResponseBody
    public ResponseEntity<BoMon> getBoMonById(@PathVariable Long id) {
        Optional<BoMon> bomonOptional = boMonRepository.findById(id);
        return bomonOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/bomon/update")
    public String updateBomon(@ModelAttribute BoMon bomon, RedirectAttributes redirectAttributes) {
        Optional<BoMon> existingBomon = boMonRepository.findById(bomon.getId());
        if (existingBomon.isPresent()) {
            boMonRepository.save(bomon);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bộ môn để cập nhật!");
        }
        return "redirect:/quanlydaotao/bomon";
    }


}
