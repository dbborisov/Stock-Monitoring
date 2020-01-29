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

public class RevolutStockListPrice extends BaseModel {
    public RevolutStockListPrice() {

    }


     @Column(name = "name")
    private String name;
     @Column(name = "full_name")
    private String fullName;

    @Column(name = "price_open")
    private String price_open;

    @Column(name = "price_close")
    private String price_close;

    @Column(name = "volume")
    private String volume;

    @Column(name = "high")
    private String high;

    @Column(name = "low")
    private String low;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Calendar createdDate;
}
