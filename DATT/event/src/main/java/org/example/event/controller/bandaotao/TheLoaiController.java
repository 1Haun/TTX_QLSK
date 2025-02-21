package org.example.event.controller.bandaotao;

import org.example.event.entity.TheLoai;
import org.example.event.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class TheLoaiController {
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    @GetMapping("/bandaotao/theloai")
    public String danhSachTheLoai(Model model) {
        List<TheLoai> theLoaiList = theLoaiRepository.findAll();
        model.addAttribute("theloai", new TheLoai());
        model.addAttribute("theloaiList", theLoaiList);
        return "bandaotao/theloai"; // Trả về trang Thymeleaf
    }
    @PostMapping("/bandaotao/theloai/add")
    public String addDepartment(@ModelAttribute TheLoai theLoai, RedirectAttributes redirectAttributes) {
        try{
            theLoaiRepository.save(theLoai);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm bộ môn thành công thành công!");
        }catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("errorMessage", "Mã bộ môn đã tồn tại!");
        }
        return "redirect:/bandaotao/theloai";
    }
    @GetMapping("/bandaotao/theloai/edit/{id}")
    @ResponseBody
    public ResponseEntity<TheLoai> getBoMonById(@PathVariable Long id) {
        Optional<TheLoai> theLoaiOptional = theLoaiRepository.findById(id);
        return theLoaiOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("bandaotao/theloai/update")
    public String updateBomon(@ModelAttribute TheLoai theloai, RedirectAttributes redirectAttributes) {
        Optional<TheLoai> existingBomon = theLoaiRepository.findById(theloai.getId());
        if (existingBomon.isPresent()) {
            theLoaiRepository.save(theloai);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bộ môn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bộ môn để cập nhật!");
        }
        return "redirect:/bandaotao/theloai";
    }

}
