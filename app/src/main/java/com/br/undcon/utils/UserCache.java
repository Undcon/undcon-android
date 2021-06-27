package com.br.undcon.utils;

import android.content.SharedPreferences;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.LoginResponseDto;
import com.google.gson.Gson;

public class UserCache {
    private static UserCache userCredential;
    private LoginResponseDto login;
    private InventoryDto inventory;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public static synchronized UserCache getInstance() {
        if (userCredential == null) userCredential = new UserCache();
        return userCredential;
    }

    public void setLogin(LoginResponseDto dto) {
        this.login = dto;
    }

    public LoginResponseDto getLogin() {
        return this.login;
    }

    public void setInventory(InventoryDto ie) {
        this.inventory = ie;
    }

    public InventoryDto getInventory() {
        return this.inventory;
    }
}
