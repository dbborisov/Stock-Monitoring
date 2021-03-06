package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.RevolutStockList;

import java.util.List;

public interface TestWebRead {
    String jsoupGoogleSearch(String content);

    String jsoupGoogleSearchPrice(String stockName);

    String htmlUtilStockInNasdaq(String stockName);

    List<RevolutStockList> getStockList();

    String htmlUtilGoogleSearch(String stockName);

    String htmlUtilGoogleSearchOnlyStock(String stockName);

}
