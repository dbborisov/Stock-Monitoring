package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.RevolutStockList;

import java.util.List;

public interface TestWebRead {
    String jsoupGoogleSearch(String content);
    String jsoupGoogleSearchPrice(String stockName);
    public List<RevolutStockList> getStockList();


}
