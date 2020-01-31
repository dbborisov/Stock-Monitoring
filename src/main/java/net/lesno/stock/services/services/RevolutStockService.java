package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.services.model.StockNameAndPriceModel;

import java.util.List;

public interface RevolutStockService  {

    boolean seedInDB (Object stock);

    List<RevolutStockList> allStocksInRevolut();
    StockNameAndPriceModel allStokAndPriceUpdate(String name);

    List<RevolutStockList> allStokAndPrice();
}
