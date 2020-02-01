package net.lesno.stock.services.services.imp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.model.RevolutStockListPrice;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.services.model.RevolutStockListPriceModel;
import net.lesno.stock.services.services.RestApiService;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestApiServiceImpl implements RestApiService {


    private final RestTemplate restTemplate;
    private final RevolutStokListRepository stokListRepository;

    public RestApiServiceImpl(RestTemplateBuilder restTemplateBuilder, RevolutStokListRepository stokListRepository) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
        this.stokListRepository = stokListRepository;
    }

    public String getPostsPlainJSON(String symbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&outputsize=full&apikey=49KXBTD3FBBZ3WL2";
        return this.restTemplate.getForObject(url, String.class);
    }

    public RevolutStockListPriceModel[] getPostsAsObject(String symbol) {
//        "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=demo  -ok"
//        ""https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=NIO&interval=5min&apikey=P1CWMMTE0GLRFBYL" -ok"
//        https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&outputsize=full&apikey=demo ok
        String url ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&outputsize=full&apikey=demo";
        String str = this.restTemplate.getForObject(url, String.class);
        str= str.substring(1,str.length()-3);
        str=str.replaceAll("(\"Meta Data\":[\\s+{\"0-9\\.A-Za-z:\\(\\),-\\/]*\\},)","")
                .replaceAll("    \"[a-zA-z\\s(\\)\\d]*\": \\{","").replaceAll("\\d\\. ","").replaceFirst("^\\{([\\n ]*)","{\n        ");

        List<String> strings = Arrays.stream(str.split("(?:}),\\n        ")).map(e -> e + "}").collect(Collectors.toList());
       RevolutStockList stock = this.stokListRepository.findByName(symbol).get();
        for (int i = 0; i < strings.size(); i++) {
            RevolutStockListPrice tempStock = new RevolutStockListPrice();
            tempStock.setName(stock.getName());

            String temp = strings.get(i);
            int bigin = temp.indexOf('"');
            int end = temp.indexOf('{')-3;
            tempStock.setCreatedDate((temp.substring(bigin,end).replaceAll("\"","")));
            String[] result=temp.substring(temp.indexOf('{')+1,temp.indexOf("}")-1).replaceAll("\\s+","").replaceAll("\"","").split(",");
            System.out.println(temp);

            for (int j = 0; j < result.length; j++) {
               String[] param = result[j].split(":");

               switch (param[0]){

                   case"open":
                       tempStock.setPrice_open(param[1]);
                       break;
                   case"high":
                       tempStock.setHigh(param[1]);
                       break;
                   case"low":
                       tempStock.setLow(param[1]);
                       break;
                   case"close":
                       tempStock.setPrice_close(param[1]);
                       break;
                   case"volume":
                       tempStock.setVolume(param[1]);
                       break;
               }
            stock.getList().add(tempStock);
            }

        }
        this.stokListRepository.save(stock);
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<RevolutStockListPriceModel> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<RevolutStockListPriceModel>>(){});

        return this.restTemplate.getForObject(str, RevolutStockListPriceModel[].class);
    }

    @Deprecated
    public Data getPostWithResponseHandling() {
        String url = "https://jsonplaceholder.typicode.com/posts/{id}";
        ResponseEntity<Data> response = this.restTemplate.getForEntity(url, Data.class, 1);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public String liveStockApi(String symbol) {
        try {
            WebReadImpl.setTrustAllCerts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String apyKey = "Lr4LouGzqSulq4vdPjNMsn8DHGN6vFIp7txhlYYQQM7LXyWoq7i6KIdwixXP";
        String url = "https://api.worldtradingdata.com/api/v1/stock?symbol="+symbol+"&api_token="+apyKey;


        String jsonString = this.restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(jsonString);
        MetaData data = null;

//        String data1 = jsonObject.get("data").toString().replaceAll("\\{\"data\":\\[", "")
//                .replaceAll("],\"symbols_requested\":2,\"symbols_returned\":1}", "");
//        data1= data1.replaceAll("\\[","").replaceAll("]","");
        try {
           data = new ObjectMapper().readValue(jsonString, MetaData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject.get("data"));


        return jsonString;

    }

}
