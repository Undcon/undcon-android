package com.br.undcon.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.br.undcon.dto.LoginResponseDto;
import com.google.gson.Gson;

public class UserCredential {
    private static UserCredential userCredential;
    private LoginResponseDto user;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public static synchronized UserCredential getInstance() {
        if (userCredential == null) userCredential = new UserCredential();
        return userCredential;
    }

    public void setUser(LoginResponseDto dto) {
        this.user = dto;
    }

    public LoginResponseDto getUser() {
        return this.user;
    }
}
