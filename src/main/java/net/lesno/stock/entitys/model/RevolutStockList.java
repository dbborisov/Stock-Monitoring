package net.lesno.stock.entitys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="revolute_stock_list")
@Getter
@Setter
@NoArgsConstructor
public class RevolutStockList extends BaseModel {

    private String name;
    private String fullName;

}
