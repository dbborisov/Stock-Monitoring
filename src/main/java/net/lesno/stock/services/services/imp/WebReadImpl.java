package net.lesno.stock.services.services.imp;

import net.lesno.stock.services.services.WebReadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebReadImpl implements WebReadService {
private List<String> list = new ArrayList<>();

    @Override
    public String readWeb(String url) throws IOException {
        Document docCustomConn = Jsoup.connect(url)
                .userAgent("Mozilla")
                .timeout(5000)
//                .cookie("cookiename", "val234")
//                .cookie("anothercookie", "ilovejsoup")
                .referrer("http://google.com")
//                .header("headersecurity", "xyz123")

                .get();


//        System.out.println(docCustomConn.getElementsByClass("element element--intraday").html());
        //        System.out.println(docCustomConn.html().replaceAll("(</)*html(>)*",""));
 return docCustomConn.getElementsByClass("element element--intraday").html();



//        return docCustomConn.html().replaceAll("(</)*html(>)*","").replaceAll("(</)*body(>)*","");
    }

    @Override
    public List<String> add(String url) {
        this.list.add(url);
        return this.list;
    }

    @Override
    public List<String> getList() {
        return this.list;
    }

}
