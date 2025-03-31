    package org.example.event.controller.home;

    import org.example.event.entity.HocKy;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;

    @Controller
    @RequestMapping("/home")
    public class HomeController {

            @GetMapping("/login")
            public String showLoginPage() {
                return "/giaodientong/login"; // Trả về template login.html trong thư mục templates/
            }
        }

