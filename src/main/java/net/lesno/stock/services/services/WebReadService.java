package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.Stock;

import java.io.IOException;
import java.util.List;

public interface WebReadService {

    String readWeb(String url) throws IOException;
    void add(String url);
    List<Stock> getList();
}
