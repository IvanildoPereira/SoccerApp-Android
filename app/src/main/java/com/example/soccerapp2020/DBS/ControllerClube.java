package com.example.soccerapp2020.DBS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.soccerapp2020.Modelos.ClubeBean;

import java.util.ArrayList;
import java.util.List;



public class ControllerClube {
    private static CriaBanco dbHelper = null;
    private static SQLiteDatabase db = null;

    public ControllerClube(Context context) {
        if (dbHelper == null ) {
            dbHelper = new CriaBanco(context);
        }
    }

    public String inserir(ClubeBean clube) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        String retorno;
        valores = new ContentValues();
        valores.put("NOME", clube.getNomeClube());
        valores.put("ANO_FUNDACAO", clube.getAnoFundacao());
        resultado = db.insert(CriaBanco.TABELA_CLUBES, null, valores);
        db.close();

        if (resultado == -1) {
            retorno = "Erro ao inserir registro";
        } else {
            retorno = "Registro Inserido com sucesso";
        }
        return retorno;
    }

    public String excluir(ClubeBean clube) {
        String retorno = "Resgistro Excluido com Sucesso";
        String where = "ID = " + clube.getId();
        db = dbHelper.getReadableDatabase();
        db.delete(CriaBanco.TABELA_CLUBES,where,null);
        db.close();
        return retorno;
    }

    public String alterar(ClubeBean clube) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        String retorno = "Registro Alterado com sucesso";
        String where = "ID = " + clube.getId();
        valores = new ContentValues();
        valores.put("NOME", clube.getNomeClube());
        valores.put("ANO_FUNDACAO", clube.getAnoFundacao());
        db.update(CriaBanco.TABELA_CLUBES, valores,where,null);
        db.close();
        return retorno;
    }

    public ClubeBean buscarClubePorId(int clubeId){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CLUBES where id = " + clubeId, null);
        ClubeBean clube = new ClubeBean(0,"","");
        if(cursor.moveToFirst()){
            clube.setId(cursor.getInt(0));
            clube.setNomeClube(cursor.getString(1));
            clube.setAnoFundacao(cursor.getString(2));
        }
        return clube;
    }

    public List<ClubeBean> listarClubes() {
        List<ClubeBean> clubes = new ArrayList<ClubeBean>();
        String selectQuery = "SELECT * FROM CLUBES" ;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ClubeBean clube = new ClubeBean(0,"","");
                clube.setId(cursor.getInt(0));
                clube.setNomeClube(cursor.getString(1));
                clube.setAnoFundacao(cursor.getString(2));
                clubes.add(clube);
            } while (cursor.moveToNext());
        }
        return clubes;
    }

    public List<ClubeBean> listarClubesPorNome(ClubeBean clubeEnt) {
        List<ClubeBean> clubes = new ArrayList<ClubeBean>();
        String parametro = clubeEnt.getNomeClube();
        String selectQuery = "SELECT ID, NOME, ANO_FUNDACAO FROM CLUBES WHERE NOME LIKE ?" ;
        String[] whereArgs = new String[] { "%" + parametro + "%"  };
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, whereArgs);
        if (cursor.moveToFirst()) {
            do {
                ClubeBean clube = new ClubeBean(0,"","");
                clube.setId(cursor.getInt(0));
                clube.setNomeClube(cursor.getString(1));
                clube.setAnoFundacao(cursor.getString(2));
                clubes.add(clube);
            } while (cursor.moveToNext());
        }
        return clubes;
    }
}
