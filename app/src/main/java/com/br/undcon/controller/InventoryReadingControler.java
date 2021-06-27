package com.br.undcon.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.br.undcon.dao.ConnectionDB;
import com.br.undcon.dao.InventoryReadingDAO;
import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.InventoryReadingCountDto;
import com.br.undcon.dto.InventoryReadingDto;
import com.br.undcon.dto.PersonDto;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.InventoryReadingStatus;
import com.br.undcon.model.SectorEntity;
import com.br.undcon.rest.service.InventoryReadingService;

import java.util.List;

public class InventoryReadingControler {
    private InventoryReadingDAO dao;
    private InventoryReadingService service;

    public InventoryReadingControler(Context context) {
        dao = new InventoryReadingDAO(context);
        service = new InventoryReadingService();
    }

    public List<InventoryReadingDto> getAll() {
        return dao.getAll();
    }

    public long insert(InventoryReadingDto dto, String code) {
        InventoryReadingCountDto res = dao.getInventoryReadingByCodeAndSetor(dto, code);
        if (res != null) {
            dto.setId(res.getId());
            dto.setQuantity(res.getCount() + 1);
            return dao.update(dto);
        } else {
            return dao.insert(dto);
        }
    }
}
