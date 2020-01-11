package net.lesno.stock.app.web;


import net.lesno.stock.services.model.WebModelPost;
import net.lesno.stock.services.services.WebReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {
    private final WebReadService webReadService;


    @Autowired
    public AddController(WebReadService webReadService) {
        this.webReadService = webReadService;

    }
    @GetMapping("/add")
    private ModelAndView addUrl(){

        return new ModelAndView("addUrl");
    }


    @PostMapping("/add")
    private String setHome(@ModelAttribute WebModelPost model) {

        this.webReadService.add(model.getUrl());

        return "redirect:/";
    }
    @GetMapping("/test")
    private ModelAndView test(){

        return  new ModelAndView("login");
    }
}
