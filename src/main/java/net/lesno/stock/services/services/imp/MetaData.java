package net.lesno.stock.services.services.imp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class MetaData implements Serializable {
   String symbols_requested;
   String symbols_returned;
   List <Data> data;

    public MetaData() {
        this.data = new ArrayList<>();
    }
}
