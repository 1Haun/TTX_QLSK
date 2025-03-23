package org.example.event.controller.sinhvien;



import org.example.event.entity.SinhVien;
import org.example.event.entity.SinhVienSuKien;
import org.example.event.entity.SuKien;
import org.example.event.repository.SinhVienRepository;
import org.example.event.repository.SinhVienSuKienRepository;
import org.example.event.repository.SuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private SuKienRepository suKienRepository;

    @Autowired
    private SinhVienSuKienRepository sinhVienSuKienRepository;

    // Hiển thị danh sách sự kiện có thể đăng ký
    @GetMapping("/dang-ky-su-kien")
    public String hienThiDanhSachSuKien(Model model) {
        List<SuKien> suKienList = suKienRepository.findAll();
        model.addAttribute("suKienList", suKienList);
        return "sinhvien/dang_ky_su_kien";  // Đảm bảo trùng với tên file trong templates/
    }


    // Xử lý đăng ký sự kiện
    @GetMapping("/dang-ky/{id}")
    public String dangKySuKien(@PathVariable("id") Long id, Model model) {
        SuKien suKien = suKienRepository.findById(id).orElse(null);
        if (suKien != null) {
            // Giả lập sinh viên (có thể thay bằng session đăng nhập)
            SinhVien sinhVien = sinhVienRepository.findByMaSinhVien("SV001");

            if (sinhVienSuKienRepository.existsBySinhVienIdAndSuKienId(sinhVien.getId(), suKien.getId())) {
                model.addAttribute("message", "Bạn đã đăng ký sự kiện này trước đó!");
            } else {
                SinhVienSuKien sinhVienSuKien = new SinhVienSuKien();
                sinhVienSuKien.setSinhVien(sinhVien);
                sinhVienSuKien.setSuKien(suKien);
                sinhVienSuKien.setDiemDanh(false);
                sinhVienSuKienRepository.save(sinhVienSuKien);
                model.addAttribute("message", "Đăng ký sự kiện thành công!");
            }
        } else {
            model.addAttribute("message", "Sự kiện không tồn tại!");
        }
        return "redirect:/sinhvien/dang-ky-su-kien";
    }

    @GetMapping("/diem-danh")
    public String hienThiDiemDanh() {
        return "sinhvien/diem_danh_su_kien"; // Đảm bảo file này tồn tại trong templates/sinhvien/
    }

    // Xử lý điểm danh sự kiện
    @PostMapping("/diem-danh")
    public String diemDanhSuKien(@RequestParam("maDiemDanh") String maDiemDanh, Model model) {
        SuKien suKien = suKienRepository.findByMaDiemDanh(maDiemDanh);

        if (suKien != null) {
            SinhVien sinhVien = sinhVienRepository.findByMaSinhVien("SV001");
            SinhVienSuKien sinhVienSuKien = sinhVienSuKienRepository.findBySinhVienIdAndSuKienId(sinhVien.getId(), suKien.getId());

            if (sinhVienSuKien != null) {
                sinhVienSuKien.setDiemDanh(true);
                sinhVienSuKienRepository.save(sinhVienSuKien);
                model.addAttribute("message", "✅ Điểm danh thành công!");
            } else {
                model.addAttribute("message", "⚠ Bạn chưa đăng ký sự kiện này, không thể điểm danh!");
            }
        } else {
            model.addAttribute("message", "❌ Mã điểm danh không hợp lệ!");
        }

        return "sinhvien/diem_danh_su_kien";
    }


}

