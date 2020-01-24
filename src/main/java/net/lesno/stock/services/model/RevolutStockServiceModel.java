package net.lesno.stock.services.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevolutStockServiceModel extends BaseServiceModel{

    private String name;
    private String fullName;
    private String price;


}
