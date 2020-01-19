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
        String doc = "";
        objectList = new ArrayList<>();
//        revolutStockService.seedInDB("some");

            for (int i = 0; i < webReadService.getList().size(); i++) {
                StringBuilder sb = new StringBuilder();
                String str = webReadService.getList().get(i).getId()+"";
                sb.append(String.format("<div id = %s class = card-%s >",str,str)+"</div>");
                try {
                    sb.append( this.webReadService.readWeb(webReadService.getList().get(i).getUrl()));
                }catch (Exception e){
                    e.printStackTrace();
                    sb.append("<div class = \"company__ticker\"No_Data</div>");
                }


//                System.out.println(sb.toString());
                if(sb.toString().trim().equals("")){
                    sb.append("No_Data");
                }
                objectList.add(sb.toString());

            }


        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("view",objectList);

        return modelAndView;
    }






}
