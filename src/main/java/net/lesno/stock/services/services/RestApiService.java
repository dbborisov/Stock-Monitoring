package net.lesno.stock.services.services;

import net.lesno.stock.entitys.model.RevolutStockListPrice;
import net.lesno.stock.services.services.imp.Post;

public interface RestApiService {
    String getPostsPlainJSON(String symbol);
    RevolutStockListPrice[] getPostsAsObject(String symbol);
    Post getPostWithResponseHandling();
}
