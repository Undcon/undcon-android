package com.br.undcon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.undcon.model.InventoryProductEntity;

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
        ContentValues values = new ContentValues();
        values.put("usuario_id", entity.getUser().getId());
        values.put("inventario_id", entity.getInventory().getId());
        values.put("gtin", entity.getGtin());
        values.put("codigo_interno", entity.getInternCode());
        values.put("descricao", entity.getDescription());
        return database.insert(TABLE_NAME, null, values);
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
        return results;
    }
}
