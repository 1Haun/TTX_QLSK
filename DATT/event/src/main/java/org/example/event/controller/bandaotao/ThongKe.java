package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongKe {
    @GetMapping("/bandaotao/thongke")
    public String thongKe() {
        return "bandaotao/thongke";
    }
}
