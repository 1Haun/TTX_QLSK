package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheLoai {
    @GetMapping("/bandaotao/theloai")
    public String thongKe() {
        return "bandaotao/theloai";
    }
}
