package net.lesno.stock.app.web;

import net.lesno.stock.services.services.WebReadService;
import net.lesno.stock.services.services.imp.RevolutStockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final WebReadService webReadService;
    private  List<Object> objectList;
    private final RevolutStockServiceImpl revolutStockService;



    @Autowired
    public HomeController(WebReadService webReadService, List<Object> objectList, RevolutStockServiceImpl revolutStockService) {
        this.webReadService = webReadService;
        this.objectList = objectList;
        this.revolutStockService = revolutStockService;
    }


    @GetMapping("/")
//    @ResponseBody
    private ModelAndView getHome() {

        objectList = new ArrayList<>();
        this.revolutStockService.saveStockPriceByDay("NIO","20");
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("view",webReadService.getList());

        return modelAndView;
    }






}
