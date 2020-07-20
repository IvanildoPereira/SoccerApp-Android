package com.example.soccerapp2020.DBS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "SOCCERAPPDB.db";
    public static final String TABELA_CLUBES = "CLUBES";
    public static final String TABELA_CAMPEOANTOS = "CAMPEONATOS";
    public static final String TABELA_CLUBECAMPEOANTO = "CLUBE_CAMPEONATO";

    private static final int VERSAO_SCHEMA = 1;
    private final String S_CREATE_CLUBES;
    private final String S_CREATE_CAMPEONATOS;
    private final String S_CREATE_CLUBECAMPEONATO;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO_SCHEMA);
        this.S_CREATE_CLUBES = "CREATE TABLE CLUBES (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME TEXT,ANO_FUNDACAO TEXT);";
        this.S_CREATE_CAMPEONATOS = "CREATE TABLE CAMPEONATOS (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME TEXT, PREMIACAO Double, LOCAL TEXT);";
        this.S_CREATE_CLUBECAMPEONATO = "CREATE TABLE CLUBE_CAMPEONATO (ID INTEGER PRIMARY KEY AUTOINCREMENT,ID_CLUBE INTEGER,ID_CAMPEOANTO INTEGER, OBS TEXT, FOREIGN KEY(ID_CLUBE) REFERENCES CLUBES(ID),FOREIGN KEY(ID_CAMPEOANTO) REFERENCES CAMPEONATOS(ID));";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(S_CREATE_CLUBES);
        db.execSQL(S_CREATE_CAMPEONATOS);
        db.execSQL(S_CREATE_CLUBECAMPEONATO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CLUBES);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CAMPEOANTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CLUBECAMPEOANTO);

        onCreate(db);
    }


}

