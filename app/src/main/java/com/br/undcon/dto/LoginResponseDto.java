package com.br.undcon.dto;

import com.br.undcon.model.ResourceType;

import java.util.List;

public class LoginResponseDto {
    private String tenant;
    private String token;
    private boolean resetPassword;
    private UserDto user;
    private List<ResourceType> resources;

    public LoginResponseDto() { }

    public LoginResponseDto(String tenant, String token, boolean resetPassword, UserDto user, List<ResourceType> resources) {
        super();
        this.tenant = tenant;
        this.token = token;
        this.resetPassword = resetPassword;
        this.user = user;
        this.resources = resources;
    }

    public String getTenant() {
        return tenant;
    }

    public String getToken() {
        return token;
    }

    public UserDto getUser() {
        return user;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public List<ResourceType> getResources() {
        return resources;
    }
}
