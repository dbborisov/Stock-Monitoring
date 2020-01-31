package net.lesno.stock.services.services;

import net.lesno.stock.services.model.RevolutStockListPriceModel;

public interface RestApiService {
    String getPostsPlainJSON(String symbol);
    RevolutStockListPriceModel[] getPostsAsObject(String symbol);
//    Data getPostWithResponseHandling();
    String liveStockApi(String symbol);
}
