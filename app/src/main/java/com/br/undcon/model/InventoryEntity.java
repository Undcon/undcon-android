package com.br.undcon.model;

import java.util.Date;

public class InventoryEntity {

    private Long id;
    private Date dateForeseen;
    private Date startDate;
    private Date endDate;
    private CustomerEntity customer;
    private UserEntity user;
    private InventoryStatus status;

    public InventoryEntity() { }

    public InventoryEntity(Long id, Date dateForeseen, Date startDate, Date endDate, CustomerEntity customer,
                           UserEntity user, InventoryStatus status) {
        super();
        this.id = id;
        this.dateForeseen = dateForeseen;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.user = user;
        this.status = status;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }
}
