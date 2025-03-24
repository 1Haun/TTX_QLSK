package org.example.event.controller.sinhvien;


import org.example.event.entity.SinhVienSuKien;
import org.example.event.repository.SinhVienSuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sinhvien/diemdanh")
public class DiemDanhController {

    @Autowired
    private SinhVienSuKienRepository sinhVienSuKienRepository;

    @GetMapping
    public String showDiemDanh(
            @RequestParam(required = false) String hoTen,
            @RequestParam(required = false) String chuyenNganh,
            @RequestParam(required = false) String hocKy,
            @RequestParam(required = false) String boMon,
            Model model) {

        List<SinhVienSuKien> filtered = sinhVienSuKienRepository.findAll().stream()
                .filter(sk -> (hoTen == null || sk.getSinhVien().getHoTen().toLowerCase().contains(hoTen.toLowerCase())))
                .filter(sk -> (chuyenNganh == null || sk.getChuyenNganh().toLowerCase().contains(chuyenNganh.toLowerCase())))
                .filter(sk -> (hocKy == null || sk.getHocKy().equalsIgnoreCase(hocKy)))
                .filter(sk -> (boMon == null || sk.getBoMon().toLowerCase().contains(boMon.toLowerCase())))
                .toList();

        model.addAttribute("suKiens", filtered);
        return "sinhvien/diem_danh_su_kien";
    }
}
