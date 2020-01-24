package net.lesno.stock.app.rest;


import net.lesno.stock.entitys.model.Stock;
import net.lesno.stock.services.services.WebReadService;
import net.lesno.stock.services.services.imp.RevolutStockServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class RestWebMain {

    private final WebReadService webReadService;
    private final RevolutStockServiceImpl revolutStockService;

    public RestWebMain(WebReadService webReadService, RevolutStockServiceImpl revolutStockService) {
        this.webReadService = webReadService;
        this.revolutStockService = revolutStockService;
    }

    @GetMapping("/api")
    private List<Stock> getApi() {

        return this.webReadService.getList();
    }

    @GetMapping("/api/all")

    private List<String> getApiData() {

        List<Stock> list = this.webReadService.getList();
        List<String> html = new ArrayList<>();
        list.forEach(e -> {
            html.add(this.webReadService.readWeb(e.getUrl()));
        });
//        System.out.println(html);

        return html;
    }

    @GetMapping("/api/stock_list")
    private List getStock() {

        return this.revolutStockService.allStocksInRevolut();

    }

    @GetMapping("/one/{id}")
    private Object getCurrency(@PathVariable Long id) {
        Stock oneById = this.webReadService.findOneById(id);

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("<div id = %s class =  uuid %s>", id, id));
        builder.append(this.webReadService.readWeb(oneById.getUrl()));

        return Collections.singletonMap("response", builder.toString());
    }


    @GetMapping("/one/page/{id}")
    private Object getHtml(@PathVariable Long id) {
        System.out.println("url is = " + id);


        return Collections.singletonMap("response", this.webReadService.readWeb(this.webReadService.findOneById(id).getUrl()));
    }


    @PostMapping(path = "/one/page/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    private Object setgetHtml(String name) {
        System.out.println("url is = " + name);


        return Collections.singletonMap("response", this.webReadService.readWebAll(name));
    }

    @GetMapping(path ="/one/page/read/{url}")
    private Object getHtmlByURL(@PathVariable String url) {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("<div id = %s class = card-%s >",url,url)+"</div>");
        try {
            sb.append( this.webReadService.readWeb("https://www.marketwatch.com/investing/stock/"+url));
        }catch (Exception e){
            e.printStackTrace();
            sb.append("<div class = \"company__ticker\">No_Data</div>");
        }
        return Collections.singletonMap("response", sb.toString());
    }


    @GetMapping(path ="/all/prices/update")
    private Object updatePricesOfStock() {


        return this.revolutStockService.allStokAndPriceUpdate();
    }
    @GetMapping(path ="/all/prices")
    private Object getPricesOfStock() {


        return this.revolutStockService.allStokAndPrice();
    }
}
