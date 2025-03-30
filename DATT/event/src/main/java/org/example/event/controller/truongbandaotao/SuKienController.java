package org.example.event.controller.truongbandaotao;

import org.example.event.entity.KeHoachSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.example.event.service.KeHoachSuKienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class SuKienController {
    @Autowired
    private KeHoachSuKienRepository kHRepository;

    @Autowired
    private KeHoachSuKienService kHService;

    @GetMapping("/truongbandaotao/quanlysukien")
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

        return "/truongbandaotao/quanlysukien";
    }


    @GetMapping("/truongbandaotao/chitiet")
    public String xemChiTietKeHoach(@RequestParam Long id, Model model, RedirectAttributes redirectAttributes) {
        System.out.println(" Đang tìm kế hoạch với ID: " + id);

        Optional<KeHoachSuKien> keHoachOpt = kHRepository.findById(id);
        if (keHoachOpt.isEmpty()) {
            System.out.println(" Không tìm thấy kế hoạch với ID: " + id);
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kế hoạch!");
            return "redirect:/truongbandaotao/quanlysukien"; // Sửa lại cho đúng
        }

        model.addAttribute("keHoach", keHoachOpt.get());
        return "truongbandaotao/chitiet";
    }

    @PostMapping("truongbandaotao/quanlysukien/pheduyet/{id}")
    public String pheDuyetKeHoach(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<KeHoachSuKien> optionalKeHoach = kHRepository.findById(id);
        if (optionalKeHoach.isPresent()) {
            KeHoachSuKien keHoach = optionalKeHoach.get();
            keHoach.setTrangThai("Đã duyệt"); // Cập nhật trạng thái
            kHRepository.save(keHoach); // Lưu vào database
            redirectAttributes.addFlashAttribute("successMessage", "Kế hoạch đã được phê duyệt.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kế hoạch.");
        }
        return "redirect:/truongbandaotao/quanlysukien"; // Điều hướng về trang danh sách
    }
    @PostMapping("truongbandaotao/quanlysukien/tuchoi/{id}")
    public String tuChoiKeHoach(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<KeHoachSuKien> optionalKeHoach = kHRepository.findById(id);
        if (optionalKeHoach.isPresent()) {
            KeHoachSuKien keHoach = optionalKeHoach.get();
            keHoach.setTrangThai("Từ chối"); // Cập nhật trạng thái
            kHRepository.save(keHoach); // Lưu vào database
            redirectAttributes.addFlashAttribute("successMessage", "Kế hoạch đã bị từ chối.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kế hoạch.");
        }
        return "redirect:/truongbandaotao/quanlysukien"; // Điều hướng về trang danh sách
    }

}

