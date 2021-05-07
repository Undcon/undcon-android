package com.br.undcon.rest.api;

import com.br.undcon.model.InventoryEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InventoryAPI {
    @GET("inventory")
    Call<List<InventoryEntity>> get();
}
