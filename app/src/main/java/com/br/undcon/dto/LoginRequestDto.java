package com.br.undcon.dto;

public class LoginRequestDto {
    private String login;
    private String password;

    public LoginRequestDto() { }

    public LoginRequestDto(String login, String pass) {
        super();
        this.login = login;
        this.password = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
