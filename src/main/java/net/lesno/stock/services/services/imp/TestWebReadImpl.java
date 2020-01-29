package net.lesno.stock.services.services.imp;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.model.TestList;
import net.lesno.stock.entitys.model.TestStockName;
import net.lesno.stock.entitys.repository.RevolutStokListPriceRepository;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.entitys.repository.TestStockNameRepository;
import net.lesno.stock.services.services.TestWebRead;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.util.List;
import java.util.Optional;

@Service
public class TestWebReadImpl implements TestWebRead {
    private final RevolutStokListRepository StockListRepository;
    private final RevolutStokListPriceRepository StockPriceRepository;
    private final TestStockNameRepository testStockNameRepository;


    @Autowired
    public TestWebReadImpl(RevolutStokListRepository stockListRepository, RevolutStokListPriceRepository stockPriceRepository, TestStockNameRepository testStockNameRepository) {
        StockListRepository = stockListRepository;
        StockPriceRepository = stockPriceRepository;
        this.testStockNameRepository = testStockNameRepository;
    }


    @Override
    public String jsoupGoogleSearch(String content) {

        Document docCustomConn = null;
        try {
            setTrustAllCerts();
            docCustomConn = Jsoup.connect("https://www.google.com/search?q=" + content).followRedirects(true)
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
            docCustomConn = Jsoup.connect("https://www.google.com/search?q=" + stockName + " price").followRedirects(true)
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
    public String htmlUtilGoogleSearch(String stockName) {
        WebClient client = new WebClient();
//        client.getOptions().setJavaScriptEnabled(true);
        HtmlPage page;
        client.getOptions().setUseInsecureSSL(true);
        try {
            setTrustAllCerts();
            page = client.getPage("https://www.google.com/search?q=" + stockName + " stock price");


            return page.asXml();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    public String htmlUtilGoogleSearchOnlyStock(String stockName) {

        WebClient client = new WebClient(BrowserVersion.CHROME);
//        client.getOptions().setJavaScriptEnabled(true);
        HtmlPage page;
        try {
            setTrustAllCerts();
            page = client.getPage("https://www.google.com/search?q=" + stockName + " stock price");

            return page.getElementById("rso").asXml();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    public String htmlUtilStockInNasdaq(String stockName) {

        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
//        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
//        client.getOptions().setJavaScriptEnabled(true);
        HtmlPage page;
        try {

            page = client.getPage("https://www.marketwatch.com/investing/stock/" + stockName);

            return page.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    @Override
    public List<RevolutStockList> getStockList() {

        return this.StockListRepository.findAll();
    }

    public void makeTest(){

        TestStockName name = new TestStockName();
        TestStockName name1 = this.testStockNameRepository.getByName("pepi").orElse(null);
        List<TestList> list;
        TestList toName = new TestList();
        name.setName("pepi");
        toName.setName(name.getName());
        toName.setPrice_open("2.5");
        toName.setPrice_close("2.3");

        if(name1 == null){
        name.setFullName("Pesho");
        name.setName("pepi");
            list = name.getList();
            list.add(toName);
            this.testStockNameRepository.save(name);
        }else{
           toName.setName(name1.getName());
           name1.getList().add(toName);
            this.testStockNameRepository.save(name1);
        }


        testStockNameRepository.flush();


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
