package net.lesno.stock.services.services.imp;

import net.lesno.stock.entitys.model.Stock;
import net.lesno.stock.entitys.repository.StockRepository;
import net.lesno.stock.services.services.WebReadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebReadImpl implements WebReadService {
private List<String> list = new ArrayList<>();
private final StockRepository stockRepository;
private final ModelMapper modelMapper;

    public WebReadImpl(StockRepository stockRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readWeb(String url)  {
        Document docCustomConn = null;
        try {
            docCustomConn = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
    //                .cookie("cookiename", "val234")
    //                .cookie("anothercookie", "ilovejsoup")
    //                .referrer("http://google.com")
    //                .header("headersecurity", "xyz123")

                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return " no Data";
        }



//        Elements allElements = docCustomConn.getAllElements();
//        System.out.println(allElements);
//        System.out.println(docCustomConn.getElementsByClass("element element--intraday").html());
        //        System.out.println(docCustomConn.html().replaceAll("(</)*html(>)*",""));
 return docCustomConn.getElementsByClass("element element--intraday").html();



//        return docCustomConn.html().replaceAll("(</)*html(>)*","").replaceAll("(</)*body(>)*","");
    }

    @Override
    public void add(String url) {

        Stock stock = new Stock();
        stock.setUrl(url);

        this.stockRepository.save(stock);


    }

    @Override
    public List<Stock> getList() {
        return this.stockRepository.findAll();
    }

}
