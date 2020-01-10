package net.lesno.stock.entitys.repository;

import net.lesno.stock.entitys.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

}
