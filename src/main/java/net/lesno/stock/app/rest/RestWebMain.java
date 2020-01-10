package net.lesno.stock.app.rest;


import com.viber.bot.Request;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;
import net.lesno.stock.entitys.model.Stock;
import net.lesno.stock.services.services.WebReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class RestWebMain {

    private final WebReadService webReadService;

    public RestWebMain(WebReadService webReadService) {
        this.webReadService = webReadService;
    }

    @GetMapping("/api")
    private List<Stock> getApi(){

        return this.webReadService.getList();
    }

    @GetMapping("/api/all")

    private List<String> getApiData(){

        List<Stock> list = this.webReadService.getList();
        List<String> html = new ArrayList<>();
        list.forEach(e-> {
            try {
                html.add(this.webReadService.readWeb(e.getUrl()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
//        System.out.println(html);

        return html;
    }


}
