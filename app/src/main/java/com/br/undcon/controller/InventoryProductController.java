package com.br.undcon.controller;

import android.content.Context;

import com.br.undcon.dao.InventoryProductDAO;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.Page;
import com.br.undcon.rest.service.InventoryProductService;

import java.util.ArrayList;
import java.util.List;

public class InventoryProductController {

    private InventoryProductDAO dao;
    private InventoryProductService service;

    public InventoryProductController(Context context) {
        dao = new InventoryProductDAO(context);
        service = new InventoryProductService();
    }

    public long insert(InventoryProductEntity entity) {
        return dao.insert(entity);
    }

    public boolean loadItens() {
        List<InventoryProductEntity> list = new ArrayList<>();
        Integer page = 0;
        long totalPage;

        Page<List<InventoryProductEntity>> results = service.getAll(page);

        list = results.getContent();
        totalPage = results.getTotalPages() - 1;

        for (InventoryProductEntity res: list) {
            insert(res);
        }

        while (page < totalPage) {
            page++;
            results = service.getAll(page);
//            list.addAll(results.getContent());
            list = results.getContent();
            for (InventoryProductEntity res: list) {
                insert(res);
            }
            System.out.println("teste");
        }



         return true;
    }

    public InventoryProductEntity getFindByCode(String gtin) {
        return dao.getFindByCode(gtin);
    }
}
