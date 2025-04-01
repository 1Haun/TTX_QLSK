package org.example.event.controller.sinhvien;

import org.example.event.entity.KeHoachSuKien;
import org.example.event.entity.SinhVien;
import org.example.event.entity.SinhVienSuKien;
import org.example.event.service.SinhVienService;
import org.example.event.service.SinhVienSuKienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sinhvien/sukien")
public class SinhVienSuKienController {

    @Autowired
    private SinhVienSuKienService sinhVienSuKienService;

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping("/dangky")
    public String hienThiTrangDangKy(Model model) {
        model.addAttribute("sinhVienSuKien", new SinhVienSuKien());
        List<SinhVienSuKien> danhSachDangKy = sinhVienSuKienService.getSuKienDaDangKy();
        model.addAttribute("danhSachDangKy", danhSachDangKy);
        // Lấy danh sách kế hoạch sự kiện đã duyệt
        List<KeHoachSuKien> keHoachDaDuyet = sinhVienSuKienService.getKeHoachDaDuyet();
        model.addAttribute("keHoachDaDuyet", keHoachDaDuyet);

        return "sinhvien/dang_ky_su_kien";
    }

    @PostMapping("/dangky")
    public String dangKySuKien(@ModelAttribute SinhVienSuKien sinhVienSuKien) {
        // Đảm bảo SinhVien đã tồn tại trong DB
        SinhVien sinhVien = sinhVienService.findOrCreateSinhVien(sinhVienSuKien.getSinhVien());
        sinhVienSuKien.setSinhVien(sinhVien);  // Gán lại đối tượng đã được lưu vào DB

        sinhVienSuKienService.dangKySuKien(sinhVienSuKien);
        return "redirect:/sinhvien/sukien/dangky";
    }
}
