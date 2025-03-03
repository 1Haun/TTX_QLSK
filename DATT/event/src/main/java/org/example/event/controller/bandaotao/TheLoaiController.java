package org.example.event.controller.bandaotao;

import org.example.event.entity.TheLoai;
import org.example.event.repository.TheLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TheLoaiController {
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    @GetMapping("/bandaotao/theloai")
    public String danhSachTheLoai(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String keyword,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TheLoai> theLoaiPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            theLoaiPage = theLoaiRepository.findTheLoaiByName(keyword, pageable);
            model.addAttribute("keyword", keyword); // Giữ lại keyword sau khi tìm kiếm
        } else {
            theLoaiPage = theLoaiRepository.findAll(pageable);
        }

        model.addAttribute("theloai", new TheLoai());
        model.addAttribute("theloaiPage", theLoaiPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", theLoaiPage.getTotalPages());

        return "bandaotao/theloai";
    }


    @PostMapping("/bandaotao/theloai/add")
    public String addCategory(@ModelAttribute TheLoai theLoai, RedirectAttributes redirectAttributes) {
        try{
            theLoaiRepository.save(theLoai);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thể loại thành công thành công!");
        }catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("errorMessage", "Mã thể loại đã tồn tại!");
        }
        return "redirect:/bandaotao/theloai";
    }
    @GetMapping("/bandaotao/theloai/edit/{id}")
    @ResponseBody
    public ResponseEntity<TheLoai> getTheLoaiById(@PathVariable Long id) {
        Optional<TheLoai> theLoaiOptional = theLoaiRepository.findById(id);
        return theLoaiOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("bandaotao/theloai/update")
    public String updateBomon(@ModelAttribute TheLoai theLoai, RedirectAttributes redirectAttributes) {
        Optional<TheLoai> existingTheLoai = theLoaiRepository.findById(theLoai.getId());
        if (existingTheLoai.isPresent()) {
            theLoaiRepository.save(theLoai);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thể loại thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy thể loại để cập nhật!");
        }
        return "redirect:/bandaotao/theloai";
    }


}
