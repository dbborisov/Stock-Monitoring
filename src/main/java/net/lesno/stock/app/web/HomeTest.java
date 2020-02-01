package net.lesno.stock.app.web;

import net.lesno.stock.services.services.WebReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeTest {
    private final WebReadService webReadService;

    public HomeTest(WebReadService webReadService) {
        this.webReadService = webReadService;
    }

    @GetMapping("/homeTest")
    public ModelAndView getHomeTest(){

        return new ModelAndView("homeTest").addObject("view",this.webReadService.getList());
    }
}
