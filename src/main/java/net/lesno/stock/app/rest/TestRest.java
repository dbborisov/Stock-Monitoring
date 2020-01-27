package net.lesno.stock.app.rest;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.services.services.TestWebRead;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRest {


    private final TestWebRead testWebRead;

    public TestRest(TestWebRead testWebRead) {
        this.testWebRead = testWebRead;
    }

    @GetMapping("/test/api/all")
    public List<RevolutStockList> getListStockName(){

      return  this.testWebRead.getStockList();
    }
    @GetMapping("/test/api/{stockName}")
    public String getListStockName(@PathVariable String stockName){

        return  this.testWebRead.jsoupGoogleSearchPrice(stockName);
    }
}
