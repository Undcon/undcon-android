package com.br.undcon.model;

public class UserEntity {
    private Long id;
    private String login;
    private String password;
    private boolean active;
    private String tokenResetarSenha;
    private PermissionEntity permission;

    protected UserEntity() {
    }

    public UserEntity(Long id) {
        super();
        this.id = id;
    }

    public UserEntity(Long id, String login, String password, PermissionEntity permission,
                      boolean active, String tokenResetarSenha) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.permission = permission;
        this.active = active;
        this.tokenResetarSenha = tokenResetarSenha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isResetPassword() {
        return this.getTokenResetarSenha() != null;
    }

    public String getTokenResetarSenha() {
        return tokenResetarSenha;
    }

    public void setTokenResetarSenha(String tokenResetarSenha) {
        this.tokenResetarSenha = tokenResetarSenha;
    }
}
