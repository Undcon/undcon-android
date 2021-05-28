package com.br.undcon.model;

public class SectorEntity {
    private String name;

    public SectorEntity() { }

    public SectorEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
