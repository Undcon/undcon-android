package com.br.undcon.rest.api;

import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.Page;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InventoryProductAPI {

        @GET("inventory/product")
        Call<Page<List<InventoryProductEntity>>> getAll(@Query("filter") String inventoryId,
                                                        @Query("page") Integer page,
                                                        @Query("size") Integer size);
}
