package net.lesno.stock.entitys.repository;

import net.lesno.stock.entitys.model.RevolutStockList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevolutStokListRepository extends JpaRepository<RevolutStockList,Long> {


    Optional<RevolutStockList> findByName(String name);


}
