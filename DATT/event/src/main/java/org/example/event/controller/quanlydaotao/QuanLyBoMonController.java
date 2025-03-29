package org.example.event.controller.quanlydaotao;

import org.example.event.entity.BoMon;
import org.example.event.repository.BoMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/quanlydaotao/quanlybomon")
public class QuanLyBoMonController {
    @Autowired
    private BoMonRepository boMonRepository;

    @GetMapping
    public String danhSachBoMon(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String keyword,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoMon> bomonPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            bomonPage = boMonRepository.findBoMonByCodeOrName(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            bomonPage = boMonRepository.findAll(pageable);
        }

        model.addAttribute("bomon", new BoMon());
        model.addAttribute("bomonPage", bomonPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bomonPage.getTotalPages());

        return "quanlydaotao/quanlybomon";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute BoMon boMon, RedirectAttributes redirectAttributes) {
        if (boMonRepository.existsByCode(boMon.getCode())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mã bộ môn đã tồn tại!");
        } else {
            boMonRepository.save(boMon);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm bộ môn thành công!");
        }
        return "redirect:/quanlydaotao/quanlybomon";
    }

    // API lấy thông tin bộ môn theo ID (trả về JSON)
    @GetMapping("/api/edit/{id}")
    @ResponseBody
    public ResponseEntity<BoMon> getBoMonById(@PathVariable Long id) {
        Optional<BoMon> bomonOptional = boMonRepository.findById(id);
        return bomonOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Chuyển hướng đến trang chỉnh sửa bộ môn
    @GetMapping("/edit-form/{id}")
    public String editBoMon(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<BoMon> bomonOptional = boMonRepository.findById(id);
        if (bomonOptional.isPresent()) {
            model.addAttribute("bomon", bomonOptional.get());
            return "quanlydaotao/edit-bomon"; // Trả về trang chỉnh sửa
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bộ môn!");
            return "redirect:/quanlydaotao/quanlybomon";
        }
    }

    @PostMapping("/update")
    public String updateBomon(@ModelAttribute BoMon bomon, RedirectAttributes redirectAttributes) {
        Optional<BoMon> existingBomon = boMonRepository.findById(bomon.getId());

        if (existingBomon.isPresent()) {
            // Kiểm tra xem mã bộ môn mới có bị trùng với bộ môn khác không
            if (!existingBomon.get().getCode().equals(bomon.getCode()) && boMonRepository.existsByCode(bomon.getCode())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Mã bộ môn đã tồn tại!");
                return "redirect:/quanlydaotao/quanlybomon";
            }

            BoMon updatedBoMon = existingBomon.get();
            updatedBoMon.setCode(bomon.getCode());
            updatedBoMon.setName(bomon.getName());
            updatedBoMon.setMailLead(bomon.getMailLead());
            updatedBoMon.setNameLead(bomon.getNameLead());

            boMonRepository.save(updatedBoMon);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bộ môn để cập nhật!");
        }
        return "redirect:/quanlydaotao/quanlybomon";
    }

    // Xóa bộ môn
    @PostMapping("/delete/{id}")
    public String deleteBoMon(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (boMonRepository.existsById(id)) {
            boMonRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Bộ môn không tồn tại!");
        }
        return "redirect:/quanlydaotao/quanlybomon";
    }
}
