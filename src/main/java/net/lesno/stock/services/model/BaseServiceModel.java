package net.lesno.stock.services.model;

public abstract class BaseServiceModel {

    private long id;

    protected BaseServiceModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
