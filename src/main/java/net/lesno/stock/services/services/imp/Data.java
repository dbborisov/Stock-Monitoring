package net.lesno.stock.services.services.imp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Data implements Serializable {
   public String symbol;
   public String name;
   public String currency;
   public String price;
   public String price_open;
   public String day_high;
   public String day_low;
 //            52_week_high
 @JsonProperty("52_week_high")
   public String week_high;
 //    52_week_low
 @JsonProperty("52_week_low")
   public String week_low;
   public String day_change;
   public String change_pct;
   public String close_yesterday;
   public String market_cap;
   public String volume;
   public String volume_avg;
   public String shares;
   public String stock_exchange_long;
   public String Exchange;
   public String stock_exchange_short;
   public String timezone;
   public String timezone_name;
   public String gmt_offset;
   public String last_trade_time;
   public String pe;
   public String eps;


}
