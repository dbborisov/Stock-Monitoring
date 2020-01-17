package net.lesno.stock.services.services.imp;

import net.lesno.stock.entitys.model.Stock;
import net.lesno.stock.entitys.repository.StockRepository;
import net.lesno.stock.services.services.WebReadService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
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
    public String readWeb(String url) {
        Document docCustomConn = null;
        try {
            setTrustAllCerts();
            docCustomConn = Jsoup.connect(url)
                    .userAgent("Jsoup client")
                    .timeout(3000).ignoreHttpErrors(true)
                    //                .cookie("cookiename", "val234").cookie("anothercookie", "ilovejsoup").referrer("http://google.com").header("headersecurity", "xyz123")

                    .get();
        } catch (Exception e) {
            e.printStackTrace();
            return "<div class = \"company__ticker\"No_Data</div>";

        }


        docCustomConn.getElementsByClass("scroll-top").remove();

//        Elements allElements = docCustomConn.getAllElements();
//        System.out.println(allElements);
//        System.out.println(docCustomConn.getElementsByClass("element element--intraday").html());
        //        System.out.println(docCustomConn.html().replaceAll("(</)*html(>)*",""));
        return docCustomConn.getElementsByClass("element element--intraday").html();


//        return docCustomConn.html().replaceAll("(</)*html(>)*","").replaceAll("(</)*body(>)*","");
    }

    @Override
    public String readWebAll(String url) {
        Document Conn = null;
        try {
            setTrustAllCerts();
            Conn = Jsoup.connect(url)//.cookie("Yahoo","B=16iuplpf205jg&b=3&s=oe; path=/; domain=.yahoo.com; Expires=Fri, 15 Jan 2021 07:53:20 GMT;")
                    .userAgent("Jsoup client")
                    .timeout(7000).get();

        } catch (IOException e) {
            e.printStackTrace();
            return " no Data";
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Conn.getElementsByTag("body").html());
        return Conn.getElementsByTag("body").html();
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

    @Override
    public Stock findOneById(Long id) {
        return this.stockRepository.findById(id).get();
    }


    private void setTrustAllCerts() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                    new HostnameVerifier() {
                        public boolean verify(String urlHostName, SSLSession session) {
                            return true;
                        }
                    });
        } catch (Exception e) {
            //We can not recover from this exception.
            e.printStackTrace();
        }
    }


}
