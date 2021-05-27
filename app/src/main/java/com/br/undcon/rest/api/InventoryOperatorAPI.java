package com.br.undcon.rest.api;

import com.br.undcon.dto.InventoryDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InventoryOperatorAPI {
//    @GET("inventory/operator/user/{id}/inventories")
//    Call<List<InventoryEntity>> findByCurrentOperator(@Path("id") long id);

    @GET("inventory/findByCurrentOperator")
    Call<List<InventoryDto>> findByCurrentOperator();
}
