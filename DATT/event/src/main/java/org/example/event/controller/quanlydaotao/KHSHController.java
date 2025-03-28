package org.example.event.controller.quanlydaotao;

import org.example.event.entity.KeHoachSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.example.event.service.KeHoachSuKienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class KHSHController {
    @Autowired
    private KeHoachSuKienRepository kHRepository;

    @Autowired
    private KeHoachSuKienService kHService;

    @GetMapping("/quanlydaotao/khsukien")
    public String keHoach(
            @RequestParam(required = false) String tenKeHoach,
            @RequestParam(required = false) String hocKy,
            @RequestParam(required = false) String trangThai,
            @RequestParam(required = false) String boMon,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        // Lấy danh sách các học kỳ, trạng thái và bộ môn từ database hoặc enum
        List<String> danhSachHocKy = List.of("Học kỳ 1", "Học kỳ 2", "Học kỳ 3");
        List<String> danhSachTrangThai = List.of("Chờ duyệt", "Đã duyệt", "Từ chối");
        List<String> danhSachBoMon = List.of("Toán", "Lý", "Hóa", "Sinh");

        // Truyền danh sách vào Model
        model.addAttribute("danhSachHocKy", danhSachHocKy);
        model.addAttribute("danhSachTrangThai", danhSachTrangThai);
        model.addAttribute("danhSachBoMon", danhSachBoMon);

        // Truyền giá trị đã chọn để giữ lại sau khi submit
        model.addAttribute("selectedHocKy", hocKy);
        model.addAttribute("selectedTrangThai", trangThai);
        model.addAttribute("selectedBoMon", boMon);
        model.addAttribute("tenKeHoach", tenKeHoach);

        // Lấy danh sách kế hoạch theo bộ lọc
        Page<KeHoachSuKien> keHoachList = kHService.searchKeHoach(tenKeHoach, hocKy, trangThai, boMon, page, size);
        model.addAttribute("keHoachList", keHoachList);

        return "quanlydaotao/khsukien";
    }

    @PostMapping("/quanlydaotao/khsukien/add")
    public String addKeHoach(@ModelAttribute KeHoachSuKien kh, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("🟢 Nhận dữ liệu từ form: " + kh);

            // Kiểm tra xem đã có kế hoạch trùng chưa (dựa vào tên hoặc tiêu chí khác)
            boolean exists = kHRepository.existsByTenKeHoach(kh.getTenKeHoach());
            if (exists) {
                redirectAttributes.addFlashAttribute("errorMessage", "Kế hoạch này đã tồn tại!");
                return "redirect:/quanlydaotao/khsukien";
            }

            // Lưu vào database
            kHRepository.save(kh);

            // Kiểm tra lại xem đã có trong DB chưa
            Optional<KeHoachSuKien> savedKH = kHRepository.findById(kh.getId());
            if (savedKH.isPresent()) {
                System.out.println("✅ Đã lưu vào DB: " + savedKH.get());
            } else {
                System.out.println("❌ Lưu thất bại!");
            }

            redirectAttributes.addFlashAttribute("successMessage", "Thêm kế hoạch thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm kế hoạch: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/quanlydaotao/khsukien";
    }

    @GetMapping("/quanlydaotao/khsukien/detail/{id}")
    public String viewKeHoachDetail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("ID nhận được: " + id);

        Optional<KeHoachSuKien> keHoachOptional = kHRepository.findById(id);

        if (keHoachOptional.isPresent()) {
            System.out.println("Tìm thấy kế hoạch: " + keHoachOptional.get().getTenKeHoach());
            model.addAttribute("keHoach", keHoachOptional.get());
            return "quanlydaotao/skdetail";
        } else {
            System.out.println("Không tìm thấy kế hoạch với ID: " + id);
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kế hoạch!");
            return "redirect:/quanlydaotao/khsukien";
        }
    }
}
