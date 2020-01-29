package net.lesno.stock.entitys.repository;

import net.lesno.stock.entitys.model.Log;
import net.lesno.stock.entitys.model.TestStockName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestStockNameRepository extends JpaRepository<TestStockName, Log> {

    Optional<TestStockName> getByName(String name);


}
