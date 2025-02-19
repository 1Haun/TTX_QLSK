package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoSo {
    @GetMapping("/bandaotao/coso")
    public String keHoach() {
        return "bandaotao/coso";
    }
}
