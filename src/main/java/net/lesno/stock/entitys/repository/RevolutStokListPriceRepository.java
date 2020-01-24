package net.lesno.stock.entitys.repository;

import net.lesno.stock.entitys.model.RevolutStockList;
import net.lesno.stock.entitys.model.RevolutStockListPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevolutStokListPriceRepository extends JpaRepository<RevolutStockListPrice,Long> {


}
