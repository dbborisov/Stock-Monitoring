package net.lesno.stock.app.rest;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.services.services.TestWebRead;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class TestRest {


    private final TestWebRead testWebRead;
    private final RevolutStokListRepository stokListRepository;

    public TestRest(TestWebRead testWebRead, RevolutStokListRepository stokListRepository) {
        this.testWebRead = testWebRead;
        this.stokListRepository = stokListRepository;
    }

    @GetMapping("/test/api/jsoup/all")
    public List<RevolutStockList> getListStockName(){

      return  this.testWebRead.getStockList();
    }


    @GetMapping("/test/api/jsoup/{stockName}")
    public String getListStockName(@PathVariable String stockName){

        return  this.testWebRead.jsoupGoogleSearchPrice(stockName);
    }


    @GetMapping("/test/api/htmlunit/{stockName}")
    public String getListStockNameHtmlUnit(@PathVariable String stockName){

        return  this.testWebRead.htmlUtilGoogleSearch(stockName);
    }


    @PostMapping(path = "/api/price/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    private void setgetHtml(String name,String price) {

     String[] priceAndProcent= price.replaceAll(" <div class=\"BNeawe iBp4i AP7Wnd\">","")
                .replaceAll("<span class=\"[a-zA-Z0-9]*\">","")
              .replaceAll("<[/a-z>]*","").replaceAll("\\s+","")
                .replaceAll(",",".").replaceAll("c[a-z=\"A-Z\\d]*>"," ").split(" ");
        Optional<RevolutStockList> byName = this.stokListRepository.findByName(name);
        byName.get().setPrice(priceAndProcent[0]);
        byName.get().setPercentchange(priceAndProcent[1]);
        this.stokListRepository.save(byName.get());
    }

}
