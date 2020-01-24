package net.lesno.stock.entitys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name="revolute_stock_list_and_price")
@Getter
@Setter
@NoArgsConstructor
public class RevolutStockListPrice extends BaseModel {
    @ManyToOne
    private RevolutStockList revolutStockList;
     @Column(name = "name")
    private String name;
     @Column(name = "full_name")
    private String fullName;
    @Column(name = "price")
    private String price;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar createdDate;
}
