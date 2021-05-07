package com.br.undcon.rest.service;

import com.br.undcon.rest.api.LoginAPI;

public class GenericService<T> {
    private T api;

//    public GenericService() {
//        this.api = ServiceGenerator.createService(this.api);
//    }

    protected T getAPI() {
        return this.api;
    }

}
