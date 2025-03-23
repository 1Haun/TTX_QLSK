package org.example.event.controller.sinhvien;

import org.example.event.entity.SinhVien;
import org.example.event.entity.SinhVienSuKien;
import org.example.event.repository.SinhVienRepository;
import org.example.event.repository.SinhVienSuKienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/sinhvien/sukien")
public class SinhVienSuKienController {

    @Autowired
    private SinhVienSuKienRepository sinhVienSuKienRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    // Hiển thị form đăng ký sự kiện
    @GetMapping("/dangky")
    public String showFormDangKy(Model model) {
        SinhVien sv = sinhVienRepository.findById(1L).orElse(null); // giả lập
        SinhVienSuKien sk = new SinhVienSuKien();
        sk.setSinhVien(sv); // gán sẵn để binding dữ liệu

        model.addAttribute("sinhVienSuKien", sk);
        return "sinhvien/dang_ky_su_kien";
    }





    // Xử lý lưu đăng ký
    @PostMapping("/dangky")
    public String saveDangKy(@ModelAttribute SinhVienSuKien sinhVienSuKien) {
        // Kiểm tra null tránh crash
        if (sinhVienSuKien.getSinhVien() == null || sinhVienSuKien.getSinhVien().getId() == null) {
            System.out.println("❌ Lỗi: Không có sinh viên được chọn hoặc thiếu ID!");
            return "redirect:/sinhvien/sukien/dangky?error=missing_sv";
        }

        // Lấy sinh viên từ database theo ID gửi lên
        Long sinhVienId = sinhVienSuKien.getSinhVien().getId();
        SinhVien sv = sinhVienRepository.findById(sinhVienId).orElse(null);

        if (sv == null) {
            System.out.println("❌ Lỗi: Không tìm thấy sinh viên trong database!");
            return "redirect:/sinhvien/sukien/dangky?error=sv_not_found";
        }

        // Gán lại entity quản lý của Hibernate
        sinhVienSuKien.setSinhVien(sv);

        sinhVienSuKienRepository.save(sinhVienSuKien);

        return "redirect:/sinhvien/sukien/danhsach";
    }




    // Hiển thị danh sách sự kiện của sinh viên
    @GetMapping("/danhsach")
    public String danhSachSuKien(Model model) {
        Long sinhVienId = 1L; // giả lập ID sinh viên đăng nhập
        List<SinhVienSuKien> suKiens = sinhVienSuKienRepository.findBySinhVien_Id(sinhVienId);
        model.addAttribute("suKiens", suKiens);

        // ⚠️ Cái này đang thiếu nè:
        model.addAttribute("sinhVienSuKien", new SinhVienSuKien());

        return "sinhvien/dang_ky_su_kien";
    }

}
