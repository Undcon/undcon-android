package com.br.undcon.model;

public class SectorEntity {
    private Integer name;

    public SectorEntity() { }

    public SectorEntity(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Setor " + name;
    }
}
