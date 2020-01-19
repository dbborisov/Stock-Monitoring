package net.lesno.stock.services.services.imp;


import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.repository.RevolutStokListRepository;
import net.lesno.stock.services.model.RevolutStockServiceModel;
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

    public RevolutStockServiceImpl(RevolutStokListRepository stockListRepository, ModelMapper modelMapper) {
        this.stokListRepository = stockListRepository;
        this.modelMapper = modelMapper;
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
}
