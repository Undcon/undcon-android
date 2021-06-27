package com.br.undcon.dto;

public class InventoryReadingCountDto {
    private Long count;
    private Long id;

    public InventoryReadingCountDto(Long count, Long id) {
        this.count = count;
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
