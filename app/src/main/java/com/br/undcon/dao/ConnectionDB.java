package com.br.undcon.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConnectionDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "undcon.db";
    private static final int VERSION = 1;
    
    public ConnectionDB(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createInventoryProduct());
        db.execSQL(createSector());
    }

    public String createInventoryProduct() {
        StringBuilder createBuilder = new StringBuilder();
        createBuilder.append(" CREATE TABLE inventario_produto(id integer primary key autoincrement,")
                .append("                                      usuario_id integer,                  ")
                .append("                                      inventario_id integer,               ")
                .append("                                      gtin VARCHAR,                        ")
                .append("                                      codigo_interno VARCHAR,              ")
                .append("                                      descricao VARCHAR);                  ");

        return createBuilder.toString();
    }

    public String createSector() {
        return "CREATE TABLE setor(name VARCHAR);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
