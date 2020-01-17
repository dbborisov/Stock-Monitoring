package net.lesno.stock.app.rest;


import com.viber.bot.Request;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;
import net.lesno.stock.entitys.model.Stock;
import net.lesno.stock.services.services.WebReadService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
            html.add(this.webReadService.readWeb(e.getUrl()));
        });
//        System.out.println(html);

        return html;
    }

    @GetMapping("/one/{id}")
    private Object getCurrency(@PathVariable Long id){
        Stock oneById = this.webReadService.findOneById(id);

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("<div id = %s class =  uuid %s>",id ,id));
        builder.append(this.webReadService.readWeb(oneById.getUrl()));

        return Collections.singletonMap("response", builder.toString()) ;
    }


    @GetMapping("/one/page/{id}")
    private Object getHtml(@PathVariable String id){
        System.out.println("url is = " + id);



        return Collections.singletonMap("response",this.webReadService.readWebAll("https://www.w3schools.com/angular/tryit.asp?filename=try_ng_http_get")) ;
    }


    @PostMapping(path = "/one/page/" ,  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
           )
    private Object setgetHtml(String name){
        System.out.println("url is = " + name);



        return Collections.singletonMap("response",  this.webReadService.readWebAll(name)) ;
          }

}
