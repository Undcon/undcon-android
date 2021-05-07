package com.br.undcon.model;

public class PermissionEntity {

    private Long id;
    private String name;

    public PermissionEntity() { }

    public PermissionEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
