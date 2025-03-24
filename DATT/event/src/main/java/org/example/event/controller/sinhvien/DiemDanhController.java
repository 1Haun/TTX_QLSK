package org.example.event.controller.sinhvien;


import org.example.event.entity.BoMon;
import org.example.event.entity.HocKy;
import org.example.event.entity.SinhVienSuKien;
import org.example.event.repository.BoMonRepository;
import org.example.event.repository.HocKyRepository;
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
    @Autowired
    private BoMonRepository boMonRepository;

    @Autowired
    private HocKyRepository hocKyRepository;

    @GetMapping
    public String showDiemDanh(
            @RequestParam(required = false) String hoTen,
            @RequestParam(required = false) String chuyenNganh,
            @RequestParam(required = false) String hocKy,
            @RequestParam(required = false) String boMon,
            Model model) {
        List<BoMon> danhSachBoMon = boMonRepository.findAll();
        List<HocKy> danhSachHocKy = hocKyRepository.findAll();
        model.addAttribute("danhSachHocKy", danhSachHocKy);
        model.addAttribute("danhSachBoMon", danhSachBoMon);
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
