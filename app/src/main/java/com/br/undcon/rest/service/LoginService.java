package com.br.undcon.rest.service;

import android.os.StrictMode;

import androidx.loader.content.AsyncTaskLoader;

import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.rest.api.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    private LoginAPI loginAPI;

    public LoginService() {
        this.loginAPI = ServiceGenerator.createService(LoginAPI.class, false);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Call<LoginResponseDto> callSync = loginAPI.login(request);
        LoginResponseDto responseDto = null;
        try {
            Response<LoginResponseDto> response = callSync.execute();
            responseDto = response.body();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responseDto;
    }

    public void login2() {

//                "gs@cliente1", "12345678"
        Call<LoginResponseDto> call = loginAPI.login(new LoginRequestDto("gs@cliente1", "12345678"));

        call.enqueue(new Callback<LoginResponseDto>() {
            @Override
            public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {

                if (response.isSuccessful()) {

                    LoginResponseDto respostaServidor = response.body();
                    if (respostaServidor != null) {
//                        UserCredential.getInstance().setUser(respostaServidor);
//                        LoginResponseDto a = UserCredential.getInstance().getUser();
                    } else {

//                        Toast.makeText(getApplicationContext(), "Não existe Bro", Toast.LENGTH_LONG).show();
                    }
                } else {

//                    Toast.makeText(getApplicationContext(), "Não existe", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDto> call, Throwable t) {
            }
        });
    }

}
