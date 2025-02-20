package org.example.event.controller.bandaotao;

import org.example.event.entity.BoMon;
import org.example.event.repository.BoMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class BoMonController {
    @Autowired
    private BoMonRepository boMonRepository;
    @GetMapping("/bandaotao/bomon")
    public String danhSachBoMon(Model model) {
        List<BoMon> bomonList = boMonRepository.findAll();
        model.addAttribute("bomon", new BoMon()); // Để hiển thị form
        model.addAttribute("bomonList", bomonList); // Danh sách bộ môn
        return "bandaotao/bomon"; // Trả về trang Thymeleaf
    }
    @PostMapping("/bandaotao/bomon/add")
    public String addDepartment(@ModelAttribute BoMon boMon, RedirectAttributes redirectAttributes) {
        try{
            boMonRepository.save(boMon);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm bộ môn thành công thành công!");
        }catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("errorMessage", "Mã bộ môn đã tồn tại!");
        }
        return "redirect:/bandaotao/bomon";
    }
    @GetMapping("/bandaotao/bomon/edit/{id}")
    @ResponseBody
    public ResponseEntity<BoMon> getBoMonById(@PathVariable Long id) {
        Optional<BoMon> bomonOptional = boMonRepository.findById(id);
        return bomonOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("bandaotao/bomon/update")
    public String updateBomon(@ModelAttribute BoMon bomon, RedirectAttributes redirectAttributes) {
        Optional<BoMon> existingBomon = boMonRepository.findById(bomon.getId());
        if (existingBomon.isPresent()) {
            boMonRepository.save(bomon);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bộ môn để cập nhật!");
        }
        return "redirect:/bandaotao/bomon";
    }

}
