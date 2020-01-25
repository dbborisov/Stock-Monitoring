package net.lesno.stock.entitys.repository;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.model.RevolutStockListPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevolutStokListPriceRepository extends JpaRepository<RevolutStockListPrice,Long> {


    Optional<RevolutStockListPrice> getByName(String name);
}
