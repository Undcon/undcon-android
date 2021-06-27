package com.br.undcon.dto;

import com.br.undcon.model.InventoryStatus;

import java.util.Date;

public class InventoryDto {

    private Long id;

    private Date dateForeseen;

    private Date startDate;

    private Date endDate;

    private PersonDto customer;

    private InventoryStatus status;

    private String label;

    public InventoryDto() {
        // TODO Auto-generated constructor stub
    }

    public InventoryDto(Long id, Date dateForeseen, Date startDate, Date endDate, PersonDto customer,
                        InventoryStatus status, String label) {
        super();
        this.id = id;
        this.dateForeseen = dateForeseen;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.status = status;
        this.label = label;
    }

    public InventoryDto(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateForeseen() {
        return dateForeseen;
    }

    public void setDateForeseen(Date dateForeseen) {
        this.dateForeseen = dateForeseen;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PersonDto getCustomer() {
        return customer;
    }

    public void setCustomer(PersonDto customer) {
        this.customer = customer;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
