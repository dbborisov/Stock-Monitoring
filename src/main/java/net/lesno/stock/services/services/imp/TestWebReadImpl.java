package net.lesno.stock.services.services.imp;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.repository.RevolutStokListPriceRepository;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.services.services.TestWebRead;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.IOException;
import java.util.List;

@Service
public class TestWebReadImpl implements TestWebRead {
    private final RevolutStokListRepository StockListRepository;
    private final RevolutStokListPriceRepository StockPriceRepository;



@Autowired
    public TestWebReadImpl(RevolutStokListRepository stockListRepository, RevolutStokListPriceRepository stockPriceRepository) {
        StockListRepository = stockListRepository;
        StockPriceRepository = stockPriceRepository;
    }


    @Override
    public String jsoupGoogleSearch(String content){

        Document docCustomConn = null;
        try {
            setTrustAllCerts();
            docCustomConn = Jsoup.connect("https://www.google.com/search?q="+content).followRedirects(true)
                    .userAgent("Mozilla/5.0")
                    .timeout(5000).ignoreHttpErrors(true)
                    .get();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        BNeawe iBp4i AP7Wnd nio
        return docCustomConn.html();
    }

    @Override
    public String jsoupGoogleSearchPrice(String stockName) {

        Document docCustomConn = null;
        try {
            setTrustAllCerts();
            docCustomConn = Jsoup.connect("https://www.google.com/search?q="+stockName+ " price").followRedirects(true)
                    .userAgent("Mozilla/5.0")
                    .timeout(5000).ignoreHttpErrors(true)
                    .get();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        BNeawe iBp4i AP7Wnd nio
        return docCustomConn.getElementsByClass("BNeawe iBp4i AP7Wnd").first().html();
    }

    @Override
    public String htmlUtilGoogleSearch(String stockName){
        WebClient client = new WebClient();
//        client.getOptions().setJavaScriptEnabled(true);
        HtmlPage page;
        try {

             page =client.getPage("https://www.google.com/search?q="+stockName+ " stock price");


            return page.asXml();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    public String htmlUtilGoogleSearchOnlyStock(String stockName){
        WebClient client = new WebClient();
//        client.getOptions().setJavaScriptEnabled(true);
        HtmlPage page;
        try {

            page =client.getPage("https://www.google.com/search?q="+stockName+ " stock price");


            return page.getElementById("rso").asXml();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    public List<RevolutStockList> getStockList(){

    return this.StockListRepository.findAll();
}


    public void setTrustAllCerts() throws Exception {
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
