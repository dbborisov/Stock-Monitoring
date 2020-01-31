package net.lesno.stock.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Calendar;
@Getter
@Setter
@NoArgsConstructor
public class RevolutStockListPriceModel {


    @JsonProperty("open")
    private String price_open;

    @JsonProperty("close")
    private String price_close;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("high")
    private String high;

    @JsonProperty("low")
    private String low;

}
