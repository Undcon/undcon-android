package com.br.undcon.rest.service;

import android.os.StrictMode;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.Page;
import com.br.undcon.rest.api.InventoryOperatorAPI;
import com.br.undcon.rest.api.InventoryProductAPI;
import com.br.undcon.utils.UserCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class InventoryProductService {
    private InventoryProductAPI api;

    public InventoryProductService() {
        this.api = ServiceGenerator.createService(InventoryProductAPI.class, true);
    }

    public Page<List<InventoryProductEntity>> getAll(Integer page) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        long inventoryId = UserCache.getInstance().getInventory().getId();
        String filter = "inventory.id=" + inventoryId;
        Call<Page<List<InventoryProductEntity>>> callSync = api.getAll(filter, page, 10000);
        try {
            Response<Page<List<InventoryProductEntity>>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
