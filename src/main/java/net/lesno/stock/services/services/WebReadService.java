package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.Stock;

import java.util.List;

public interface WebReadService {

    String readWeb(String url) ;

    String readWebPrice(String url);

    String readWebAll(String url);
    void add(String url);
    List<Stock> getList();

    Stock findOneById(Long id);


}
