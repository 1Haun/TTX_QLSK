package org.example.event.controller.bandaotao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KeHoach {
    @GetMapping("/bandaotao/kehoach")
    public String keHoach() {
        return "bandaotao/kehoach";
    }
}
