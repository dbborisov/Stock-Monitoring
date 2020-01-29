package net.lesno.stock.entitys.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "test_stock")
@Transactional
public class TestStockName extends BaseModel{
    public TestStockName() {
        this.list = new ArrayList<>();
    }

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "fullName",nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "id", targetEntity = TestList.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestList> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<TestList> getList() {
        return list;
    }

    public void setList(List<TestList> list) {
        this.list = list;
    }
}
