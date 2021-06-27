package com.br.undcon.rest.service;

import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.utils.UserCache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (!"/posts".contains(originalRequest.url())) {
//            return chain.proceed(originalRequest);
//        }

        LoginResponseDto user = UserCache.getInstance().getLogin();
        String token = "";
        if (user != null) {
            token = user.getToken();
        }

        Request newRequest = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .build();

        return chain.proceed(newRequest);
    }
}
