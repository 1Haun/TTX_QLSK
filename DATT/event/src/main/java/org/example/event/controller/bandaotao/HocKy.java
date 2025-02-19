package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HocKy {
    @GetMapping("/bandaotao/hocky")
    public String keHoach() {
        return "bandaotao/hocky";
    }
}
