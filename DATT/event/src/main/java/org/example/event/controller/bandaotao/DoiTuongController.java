package org.example.event.controller.bandaotao;

import org.example.event.entity.DoiTuong;
import org.example.event.entity.TheLoai;
import org.example.event.repository.DoiTuongRepository;
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
public class DoiTuongController {
    @Autowired
    private DoiTuongRepository dtRepo;
    @GetMapping("/bandaotao/doituong")
    public String danhSachDoiTuong(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String keyword,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DoiTuong> doiTuongPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            doiTuongPage = dtRepo.findDoiTuongByCodeOrName(keyword, pageable);
            model.addAttribute("keyword", keyword); // Giữ lại keyword sau khi tìm kiếm
        } else {
            doiTuongPage = dtRepo.findAll(pageable);
        }

        model.addAttribute("doituong", new DoiTuong());
        model.addAttribute("doituongPage", doiTuongPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", doiTuongPage.getTotalPages());

        return "bandaotao/doituong";
    }

    @PostMapping("/bandaotao/doituong/add")
    public String addDoiTuong(@ModelAttribute DoiTuong dt, RedirectAttributes redirectAttributes) {
        try{
            dtRepo.save(dt);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm đối tượng thành công thành công!");
        }catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("errorMessage", "Mã đối tượng đã tồn tại!");
        }
        return "redirect:/bandaotao/doituong";
    }

    @GetMapping("/bandaotao/doituong/edit/{id}")
    @ResponseBody
    public ResponseEntity<DoiTuong> getDoiTuongById(@PathVariable Long id) {
        Optional<DoiTuong> dtOptional = dtRepo.findById(id);
        return dtOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("bandaotao/doituong/update")
    public String updateDoiTuong(@ModelAttribute DoiTuong dt, RedirectAttributes redirectAttributes) {
        Optional<DoiTuong> existingDT = dtRepo.findById(dt.getId());
        if (existingDT.isPresent()) {
            dtRepo.save(dt);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy đối tượng để cập nhật!");
        }
        return "redirect:/bandaotao/doituong";
    }
}
