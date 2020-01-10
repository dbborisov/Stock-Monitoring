package net.lesno.stock.entitys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stock extends BaseModel implements Serializable {
    @Column(name = "url",nullable = false)
    private String url;
    @Column(name = "tag")
    private String tag;
}
