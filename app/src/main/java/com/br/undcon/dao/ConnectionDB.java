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
        db.execSQL(createInventoryReading());
    }

    public String createInventoryProduct() {
        StringBuilder createBuilder = new StringBuilder();
        createBuilder.append(" CREATE TABLE inventario_produto(id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("                                      usuario_id INTEGER,                   ")
                .append("                                      inventario_id INTEGER,                ")
                .append("                                      gtin VARCHAR,                         ")
                .append("                                      codigo_interno VARCHAR,               ")
                .append("                                      descricao VARCHAR);                   ");

        return createBuilder.toString();
    }

    public String createSector() {
        return "CREATE TABLE setor(id INTEGER PRIMARY KEY AUTOINCREMENT, name INTEGER);";
    }

    public String createInventoryReading() {
        StringBuilder createBuilder = new StringBuilder();
        createBuilder.append(" CREATE TABLE leitura_inventario(id INTEGER PRIMARY KEY AUTOINCREMENT,        ")
                .append("                                      inventario_produto_id INTEGER,               ")
                .append("                                      quantidade INTEGER,                          ")
                .append("                                      setor INTEGER,                               ")
//                .append("                                      operador_id INTEGER,                         ")
                .append("                                      inventario_id INTEGER);                      ");
//                .append("                                      status INTEGER);                             ");

        return createBuilder.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
