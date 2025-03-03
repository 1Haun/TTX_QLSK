    package org.example.event.controller.chunghiembomon;

    import org.example.event.entity.ChuyenNganh;
    import org.example.event.service.ChuyenNganhService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    @Controller
    @RequestMapping("/chunghiembomon")
    public class ChuyenNganhController {
        @Autowired
        private ChuyenNganhService chuyenNganhService;

        @GetMapping("/chuyen-nganh")
        public String showChuyenNganh(@RequestParam(required = false) String maChuyenNganh,
                                      @RequestParam(required = false) String tenChuyenNganh,
                                      Pageable pageable,
                                      Model model) {
            Page<ChuyenNganh> page = chuyenNganhService.filterChuyenNganh(maChuyenNganh, tenChuyenNganh, pageable);
            model.addAttribute("chuyenNganhList", page);
            model.addAttribute("maChuyenNganh", maChuyenNganh);
            model.addAttribute("tenChuyenNganh", tenChuyenNganh);
            return "chunghiembomon/chuyen-nganh";
        }

        @GetMapping("/search")
        public Page<ChuyenNganh> searchChuyenNganh(@RequestParam(required = false) String maChuyenNganh,
                                                   @RequestParam(required = false) String tenChuyenNganh,
                                                   Pageable pageable) {
            return chuyenNganhService.filterChuyenNganh(maChuyenNganh, tenChuyenNganh, pageable);
        }
        @GetMapping("/chuyen-nganh/{id}")
        public String detailChuyenNganh(@PathVariable Long id, Model model) {
            ChuyenNganh chuyenNganh = chuyenNganhService.getChuyenNganhById(id);
            if (chuyenNganh == null) {
                return "redirect:/chunghiembomon/chuyen-nganh"; // Nếu không tìm thấy, quay lại danh sách
            }
            model.addAttribute("chuyenNganh", chuyenNganh);
            return "chunghiembomon/chuyen-nganh-detail"; // Trả về trang chi tiết
        }

    }



