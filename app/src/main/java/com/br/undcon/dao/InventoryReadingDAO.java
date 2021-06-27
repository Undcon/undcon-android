package com.br.undcon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.InventoryProductDto;
import com.br.undcon.dto.InventoryReadingCountDto;
import com.br.undcon.dto.InventoryReadingDto;
import com.br.undcon.dto.PersonDto;
import com.br.undcon.model.InventoryEntity;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.InventoryReadingStatus;
import com.br.undcon.model.UserEntity;
import com.br.undcon.utils.UserCache;

import java.util.ArrayList;
import java.util.List;

public class InventoryReadingDAO {
    private static final String TABLE_NAME = "leitura_inventario";
    private ConnectionDB connectionDB;
    private SQLiteDatabase database;

    public InventoryReadingDAO(Context context) {
        connectionDB = new ConnectionDB(context);
        database = connectionDB.getWritableDatabase();
    }

    public long insert(InventoryReadingDto dto) {
        ContentValues value = new ContentValues();
        value.put("inventario_produto_id", dto.getProduct().getId());
        value.put("quantidade", dto.getQuantity());
        value.put("setor", dto.getSector());
//        value.put("operador_id", dto.getOperator().getId());
//        value.put("status", 0);
        value.put("inventario_id", UserCache.getInstance().getInventory().getId());
        return database.insert(TABLE_NAME, null, value);
    }

    public long update(InventoryReadingDto dto) {
        ContentValues value = new ContentValues();
        value.put("id", dto.getId());
        value.put("quantidade", dto.getQuantity());
        long res = database.update(TABLE_NAME, value, null, null);
        return res;
    }

    public InventoryReadingCountDto getInventoryReadingByCodeAndSetor(InventoryReadingDto dto, String code) {
        String select = new StringBuilder()
                .append(" SELECT li.id, li.quantidade FROM leitura_inventario li ")
                .append(" INNER JOIN inventario_produto ip ON (li.inventario_produto_id = ip.id) ")
                .append(" WHERE (ip.gtin = ? OR ip.codigo_interno = ?) ")
                .append(" AND li.setor = ? AND li.inventario_id = ? ")
                .append(" GROUP BY li.id, li.quantidade ").toString();

        Long inventoryId = UserCache.getInstance().getInventory().getId();
        Cursor cursor = database.rawQuery(select, new String [] {code, code, dto.getSector().toString(), inventoryId.toString()});
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return new InventoryReadingCountDto(cursor.getLong(0), cursor.getLong(1));
                }
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    public List<InventoryReadingDto> getAll() {
        String select = new StringBuilder()
                .append(" SELECT li.id, ip.gtin, ip.codigo_interno, ip.descricao, li.quantidade FROM leitura_inventario li ")
                .append(" INNER JOIN inventario_produto ip ON (li.inventario_produto_id = ip.id) ORDER BY ip.descricao")
                .toString();

        Cursor cursor = database.rawQuery(select, new String [] {});
        List<InventoryReadingDto> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            InventoryReadingDto entity = new InventoryReadingDto();
            entity.setId(new Long(cursor.getLong(0)));
            entity.setProduct(new InventoryProductDto(cursor.getString(1),
                                                      cursor.getString(2),
                                                      cursor.getString(3)));
            entity.setQuantity(cursor.getLong(4));
            results.add(entity);
        }
        return results;
    }
}
