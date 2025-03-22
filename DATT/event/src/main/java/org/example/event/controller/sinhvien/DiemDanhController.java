package org.example.event.controller.sinhvien;

import org.example.event.entity.SinhVien;
import org.example.event.repository.SinhVienRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
public class DiemDanhController {
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @GetMapping("/sinhvien/diemdanh")
    public String danhSachSinhVien(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) String keyword,
                                   Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SinhVien> sinhVienPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            sinhVienPage = sinhVienRepository.findSinhVienByNameOrObject(keyword, pageable);
            model.addAttribute("keyword", keyword); // Giữ lại keyword sau khi tìm kiếm
        } else {
            sinhVienPage = sinhVienRepository.findAll(pageable);
        }

        model.addAttribute("sinhvien", new SinhVien());
        model.addAttribute("sinhvienPage", sinhVienPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sinhVienPage.getTotalPages());

        return "sinhvien/diemdanh";
    }


    @PostMapping("/sinhvien/diemdanh/add")
    public String addStudents(@ModelAttribute SinhVien sinhVien, RedirectAttributes redirectAttributes) {
        try {
            sinhVienRepository.save(sinhVien);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm sinh viên thành công thành công!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mã sinh viên không tồn tại!");
        }
        return "redirect:/sinhvien/diemdanh";
    }
}