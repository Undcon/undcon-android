package com.br.undcon.dto;

import com.br.undcon.model.InventoryReadingStatus;

public class InventoryReadingDto {
    private Long id;
    private InventoryProductDto product;
    private Long quantity;
    private Integer sector;
    private PersonDto operator;
    private InventoryDto inventory;
    private InventoryReadingStatus status;

    public InventoryReadingDto(InventoryProductDto product, Long quantity, Integer sector,
                               InventoryDto inventory) {
        super();
        this.product = product;
        this.quantity = quantity;
        this.sector = sector;
        this.inventory = inventory;
    }

    public InventoryReadingDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InventoryProductDto getProduct() {
        return product;
    }

    public void setProduct(InventoryProductDto product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public PersonDto getOperator() {
        return operator;
    }

    public void setOperator(PersonDto operator) {
        this.operator = operator;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDto inventory) {
        this.inventory = inventory;
    }

    public InventoryReadingStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryReadingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return product.getDescription() + " - " + product.getGtin() + " (" + quantity + ")";
    }
}
