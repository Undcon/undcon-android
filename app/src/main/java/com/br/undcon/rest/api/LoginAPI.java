package com.br.undcon.rest.api;

import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginAPI {
    @POST("login")
    Call<LoginResponseDto> login(@Body LoginRequestDto request);
}
