package net.lesno.stock.services.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StockNameAndPriceModel {
    private String name;
    private String fullName;
    private String price;
}
