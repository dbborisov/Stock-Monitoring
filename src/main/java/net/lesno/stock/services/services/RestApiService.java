package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.services.model.RevolutStockListPriceModel;
import org.json.JSONObject;

import java.util.List;

public interface RestApiService {
    String getPostsPlainJSON(String symbol);
    RevolutStockListPriceModel[] getPostsAsObject(String symbol);
//    Data getPostWithResponseHandling();
    String liveStockApi(String symbol);
    List<RevolutStockList> findAllStockNames(String symbol);
    JSONObject setPricesToStock(String symbols);
}
