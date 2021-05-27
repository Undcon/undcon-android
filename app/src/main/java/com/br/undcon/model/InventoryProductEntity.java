package com.br.undcon.model;

public class InventoryProductEntity {
    private Long id;
    private UserEntity user;
    private InventoryEntity inventory;
    private String gtin;
    private String internCode;
    private String description;

    public InventoryProductEntity() { }

    public InventoryProductEntity(Long id, UserEntity user, InventoryEntity inventory, String gtin, String internCode,
                                  String description) {
        super();
        this.id = id;
        this.user = user;
        this.inventory = inventory;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
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
