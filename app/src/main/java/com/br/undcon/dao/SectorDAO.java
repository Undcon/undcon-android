package com.br.undcon.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.undcon.model.SectorEntity;

import java.util.ArrayList;
import java.util.List;

public class SectorDAO {
    private static final String TABLE_NAME = "setor";
    private ConnectionDB connectionDB;
    private SQLiteDatabase database;

    public SectorDAO(Context context) {
        connectionDB = new ConnectionDB(context);
        database = connectionDB.getWritableDatabase();
    }

    public long insert(SectorEntity entity) {
        ContentValues values = new ContentValues();
        values.put("name", entity.getName());
        return database.insert(TABLE_NAME, null, values);
    }

    public List<SectorEntity> getAll() {
        List<SectorEntity> results = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, new String[]{"name"}, null, null, null, null, "name");
        while (cursor.moveToNext()) {
            SectorEntity entity = new SectorEntity();
            entity.setName(cursor.getString(0));
            results.add(entity);
        }
        return results;
    }
}
