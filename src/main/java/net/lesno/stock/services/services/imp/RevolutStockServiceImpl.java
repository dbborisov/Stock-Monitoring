package net.lesno.stock.services.services.imp;


import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.model.RevolutStockListPrice;
import net.lesno.stock.entitys.repository.RevolutStokListPriceRepository;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.services.model.StockNameAndPriceModel;
import net.lesno.stock.services.services.RevolutStockService;
import net.lesno.stock.services.services.tools.FileReader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RevolutStockServiceImpl implements RevolutStockService {

    private final RevolutStokListRepository stokListRepository;
    private final ModelMapper modelMapper;
    private final WebReadImpl webRead;
    private final RevolutStokListPriceRepository price;


    public RevolutStockServiceImpl(RevolutStokListRepository stockListRepository, ModelMapper modelMapper, WebReadImpl webRead, RevolutStokListPriceRepository price) {
        this.stokListRepository = stockListRepository;
        this.modelMapper = modelMapper;
        this.webRead = webRead;

        this.price = price;
    }


    @Override
    public boolean seedInDB(Object stock) {
        FileReader reader = new FileReader();
        Map<String, String> stringMap = reader.readFileToMap("C:\\CCB\\all_stocks.txt");
        List<RevolutStockList> revolutStockLists = new ArrayList<>();
        stringMap.entrySet().forEach((k)-> {
            RevolutStockList revolutStockList = new RevolutStockList();
            revolutStockList.setName(k.getKey());
            revolutStockList.setFullName(k.getValue());
            revolutStockLists.add(revolutStockList);
        });
        this.stokListRepository.saveAll(revolutStockLists);
        return true;
    }

    @Override
    public List<RevolutStockList> allStocksInRevolut() {
        return this.stokListRepository.findAll();
    }

    @Override
    public List<StockNameAndPriceModel> allStokAndPriceUpdate() {
        List<StockNameAndPriceModel> returnList = new ArrayList<>();
        List<RevolutStockList> all = this.stokListRepository.findAll();
        int size = all.size()-1;
        while (size > 0){
            StockNameAndPriceModel stockNameAndPriceModel = new StockNameAndPriceModel();
            stockNameAndPriceModel.setName(all.get(size).getName()); ;
            String stokReadet = this.webRead.readWebPrice("https://www.marketwatch.com/investing/stock/" + all.get(size).getName());
            stockNameAndPriceModel.setFullName(all.get(size).getFullName());
            System.out.println(stokReadet);
            if(stokReadet.equals("<div class = \"company__ticker\">No_Data</div>")){
              continue;
            }
            stockNameAndPriceModel.setPrice(stokReadet);
            returnList.add(stockNameAndPriceModel);
            this.price.save(this.modelMapper.map(stockNameAndPriceModel, RevolutStockListPrice.class));
            System.out.println("==========================="+ size+"=================");
            size-=1;
        }

        return returnList;
    }

    @Override
    public List<StockNameAndPriceModel> allStokAndPrice() {
        return this.price.findAll().stream().map(e->this.modelMapper.map(e,StockNameAndPriceModel.class)).collect(Collectors.toList());
    }
}
