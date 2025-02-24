package org.example.event.controller.bandaotao;

import org.example.event.service.KeHoachSuKienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.event.entity.KeHoachSuKien;
import org.example.event.repository.KeHoachSuKienRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KeHoachController {
    @Autowired
    private KeHoachSuKienRepository kHRepository;

    @Autowired
    private KeHoachSuKienService kHService;

    @GetMapping("/bandaotao/kehoach")
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

        return "bandaotao/kehoach";
    }

}
