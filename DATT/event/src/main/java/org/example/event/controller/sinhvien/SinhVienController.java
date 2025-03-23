//package org.example.event.controller.sinhvien;
//
//
//
//import org.example.event.entity.SinhVien;
//import org.example.event.entity.SinhVienSuKien;
//import org.example.event.entity.SuKien;
//import org.example.event.repository.SinhVienRepository;
//import org.example.event.repository.SinhVienSuKienRepository;
//import org.example.event.repository.SuKienRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/sinhvien")
//public class SinhVienController {
//
//    @Autowired
//    private SinhVienRepository sinhVienRepository;
//
//    @Autowired
//    private SuKienRepository suKienRepository;
//
//    @Autowired
//    private SinhVienSuKienRepository sinhVienSuKienRepository;
//
//
//
//    @GetMapping("/diem-danh")
//    public String hienThiDiemDanh() {
//        return "sinhvien/diem_danh_su_kien"; // Đảm bảo file này tồn tại trong templates/sinhvien/
//    }
//
//    // Xử lý điểm danh sự kiện
//    @PostMapping("/diem-danh")
//    public String diemDanhSuKien(@RequestParam("maDiemDanh") String maDiemDanh, Model model) {
//        SuKien suKien = suKienRepository.findByMaDiemDanh(maDiemDanh);
//
//        if (suKien != null) {
//            SinhVien sinhVien = sinhVienRepository.findByMaSinhVien("SV001");
////            SinhVienSuKien sinhVienSuKien = sinhVienSuKienRepository.findBySinhVienIdAndSuKienId(sinhVien.getId(), suKien.getId());
//
////            if (sinhVienSuKien != null) {
//////                sinhVienSuKien.setDiemDanh(true);
////                sinhVienSuKienRepository.save(sinhVienSuKien);
////                model.addAttribute("message", "✅ Điểm danh thành công!");
////            } else {
////                model.addAttribute("message", "⚠ Bạn chưa đăng ký sự kiện này, không thể điểm danh!");
////            }
////        } else {
////            model.addAttribute("message", "❌ Mã điểm danh không hợp lệ!");
////        }
////
////        return "sinhvien/diem_danh_su_kien";
//    }
//
//
//}
//
