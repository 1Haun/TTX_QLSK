package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoiTuong {
    @GetMapping("/bandaotao/doituong")
    public String keHoach() {
        return "bandaotao/doituong";
    }
}
