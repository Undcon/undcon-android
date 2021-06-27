package com.br.undcon.dto;

public class InventoryProductDto {
    private Long id;
    private String gtin;
    private String internCode;
    private String description;

    public InventoryProductDto() {
    }

    public InventoryProductDto(Long id, String gtin, String internCode, String description) {
        super();
        this.id = id;
        this.gtin = gtin;
        this.internCode = internCode;
        this.description = description;
    }

    public InventoryProductDto(String gtin, String internCode, String description) {
        super();
        this.gtin = gtin;
        this.internCode = internCode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getInternCode() {
        return internCode;
    }

    public void setInternCode(String internCode) {
        this.internCode = internCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
