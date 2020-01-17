package net.lesno.stock.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadPage {


    @GetMapping("/one/page/")
    private Object readOnePage(){

        return "readWeb";
    }

}
