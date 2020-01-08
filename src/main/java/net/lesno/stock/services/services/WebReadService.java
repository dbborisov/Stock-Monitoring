package net.lesno.stock.services.services;

import java.io.IOException;
import java.util.List;

public interface WebReadService {

    String readWeb(String url) throws IOException;
    List<String> add(String url);
    List<String> getList();
}
