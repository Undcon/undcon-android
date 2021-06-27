package com.br.undcon.rest.service;

import com.br.undcon.rest.api.InventoryReadingAPI;

public class InventoryReadingService {
    private InventoryReadingAPI api;

    public InventoryReadingService() {
        this.api = ServiceGenerator.createService(InventoryReadingAPI.class, false);
    }
}
