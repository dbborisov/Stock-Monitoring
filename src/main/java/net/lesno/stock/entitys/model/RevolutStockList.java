package net.lesno.stock.entitys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="revolute_stock_list")
@Getter
@Setter
@NoArgsConstructor
public class RevolutStockList extends BaseModel {
     @Column(name = "name")
    private String name;
     @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "id",targetEntity = RevolutStockListPrice.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RevolutStockListPrice> list;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Calendar lastUpdate;

}
