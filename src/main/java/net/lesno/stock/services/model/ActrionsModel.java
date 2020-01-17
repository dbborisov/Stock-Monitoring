package net.lesno.stock.services.model;

import java.util.TreeMap;

public class ActrionsModel {

    private double actions;
    private double currPrice;
    private String stockName;
    private TreeMap<String,String> moreComponents;

    public ActrionsModel() {
    }

    public ActrionsModel(double actions, String stockName) {
        this.actions = actions;
        this.stockName = stockName;
    }

    public double getActions() {
        return actions;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public TreeMap<String, String> getMoreComponents() {
        return moreComponents;
    }

    public void setActions(double actions) {
        this.actions = actions;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setMoreComponents(TreeMap<String, String> moreComponents) {
        this.moreComponents = moreComponents;
    }
}
