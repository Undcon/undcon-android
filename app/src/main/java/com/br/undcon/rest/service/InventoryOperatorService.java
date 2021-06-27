package com.br.undcon.rest.service;

import android.os.StrictMode;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.model.InventoryEntity;
import com.br.undcon.rest.api.InventoryOperatorAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class InventoryOperatorService {

    private InventoryOperatorAPI inventoryAPI;

    public InventoryOperatorService() {
        this.inventoryAPI = ServiceGenerator.createService(InventoryOperatorAPI.class, true);
    }

    public List<InventoryDto> findByCurrentOperator() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

//        UserCache.getInstance().getUser().getUser().getId()
        Call<List<InventoryDto>> callSync = inventoryAPI.findByCurrentOperator();
        List<InventoryDto> inventories = new ArrayList<>();
        try {
            Response<List<InventoryDto>> response = callSync.execute();
            inventories.addAll(response.body());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return inventories;
    }
}
