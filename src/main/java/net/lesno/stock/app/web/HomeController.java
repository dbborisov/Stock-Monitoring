package net.lesno.stock.app.web;

import net.lesno.stock.services.model.WebModelPost;
import net.lesno.stock.services.services.WebReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final WebReadService webReadService;


    @Autowired
    public HomeController(WebReadService webReadService) {
        this.webReadService = webReadService;

    }


    @GetMapping("/")
//    @ResponseBody
    private ModelAndView getHome() {
        String doc = "";
        List<String> list = new ArrayList<>();
        try {
            for (int i = 0; i < webReadService.getList().size(); i++) {
                doc = this.webReadService.readWeb(webReadService.getList().get(i));
                list.add(doc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("view",list);

        return modelAndView;
    }




}
