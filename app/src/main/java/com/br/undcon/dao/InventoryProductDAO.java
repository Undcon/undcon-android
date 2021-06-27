package com.br.undcon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.undcon.model.InventoryEntity;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class InventoryProductDAO {

    private static final String TABLE_NAME = "inventario_produto";
    private ConnectionDB connectionDB;
    private SQLiteDatabase database;

    public InventoryProductDAO(Context context) {
        connectionDB = new ConnectionDB(context);
        database = connectionDB.getWritableDatabase();
    }

    public long insert(InventoryProductEntity entity) {
        ContentValues value = new ContentValues();
        value.put("id", entity.getId());
        value.put("usuario_id", entity.getUser().getId());
        value.put("inventario_id", entity.getInventory().getId());
        value.put("gtin", entity.getGtin());
        value.put("codigo_interno", entity.getInternCode());
        value.put("descricao", entity.getDescription());
        return database.insert(TABLE_NAME, null, value);
    }

    public List<InventoryProductEntity> getAll() {
        List<InventoryProductEntity> results = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, new String[]{"id", "gtin", "codigo_interno", "descricao"}, null, null, null, null, "id");
        while (cursor.moveToNext()) {
            InventoryProductEntity entity = new InventoryProductEntity();
            entity.setId(new Long(cursor.getLong(0)));
            entity.setGtin(cursor.getString(1));
            entity.setInternCode(cursor.getString(2));
            entity.setDescription(cursor.getString(3));
            results.add(entity);
        }
        cursor.close();
        return results;
    }

    public InventoryProductEntity getFindByCode(String code) {
        String select = new StringBuilder()
        .append(" SELECT id, usuario_id, inventario_id, gtin, codigo_interno, descricao ")
        .append(" FROM inventario_produto WHERE gtin = ? OR codigo_interno = ?").toString();

        Cursor cursor = database.rawQuery(select, new String [] {code, code});
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    InventoryProductEntity entity = new InventoryProductEntity();
                    entity.setId(new Long(cursor.getInt(0)));
                    entity.setUser(new UserEntity(new Long(cursor.getInt(1))));
                    entity.setInventory(new InventoryEntity(new Long(cursor.getInt(2))));
                    entity.setGtin(cursor.getString(3));
                    entity.setInternCode(cursor.getString(4));
                    entity.setDescription(cursor.getString(5));
                    return entity;
                }
            } finally {
                cursor.close();
            }
        }
        return null;
    }

}
