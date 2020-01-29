package net.lesno.stock.entitys.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "test_stock_list")
@Transactional
public class TestList extends BaseModel{
    public TestList() {
    }

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price_open",nullable = false)
    private String price_open;
    @Column(name = "price_close",nullable = false)
    private String price_close;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Calendar createdDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_open() {
        return price_open;
    }

    public void setPrice_open(String price_open) {
        this.price_open = price_open;
    }

    public String getPrice_close() {
        return price_close;
    }

    public void setPrice_close(String price_close) {
        this.price_close = price_close;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }
}
