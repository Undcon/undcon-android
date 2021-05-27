package com.br.undcon.utils;

import android.content.SharedPreferences;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.LoginResponseDto;
import com.google.gson.Gson;

public class UserCache {
    private static UserCache userCredential;
    private LoginResponseDto user;
    private InventoryDto inventory;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public static synchronized UserCache getInstance() {
        if (userCredential == null) userCredential = new UserCache();
        return userCredential;
    }

    public void setUser(LoginResponseDto dto) {
        this.user = dto;
    }

    public LoginResponseDto getUser() {
        return this.user;
    }

    public void setInventory(InventoryDto ie) {
        this.inventory = ie;
    }

    public InventoryDto getInventory() {
        return this.inventory;
    }
}
