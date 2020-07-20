package com.example.soccerapp2020.DBS;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.soccerapp2020.Modelos.CampeonatoBean;

import java.util.ArrayList;
import java.util.List;

public class ControllerCampeonato {
    private static CriaBanco dbHelper = null;
    private static SQLiteDatabase db = null;

    public ControllerCampeonato(Context context) {
        if (dbHelper == null ) {
            dbHelper = new CriaBanco(context);
        }
    }

    public String inserir(CampeonatoBean campeonato) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        String retorno;
        valores = new ContentValues();
        valores.put("NOME", campeonato.getNomeCampeonato());
        valores.put("PREMIACAO", campeonato.getPremiacao());
        valores.put("LOCAL", campeonato.getLocal());
        resultado = db.insert(CriaBanco.TABELA_CAMPEOANTOS, null, valores);
        db.close();

        if (resultado == -1) {
            retorno = "Erro ao inserir registro";
        } else {
            retorno = "Registro Inserido com sucesso";
        }
        return retorno;
    }

    public String excluir(CampeonatoBean campeonato) {
        String retorno = "Resgistro Excluido com Sucesso";
        String where = "ID = " + campeonato.getId();
        db = dbHelper.getReadableDatabase();
        db.delete(CriaBanco.TABELA_CAMPEOANTOS,where,null);
        db.close();
        return retorno;
    }

    public String alterar(CampeonatoBean campeonato) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        String retorno = "Registro Alterado com sucesso";
        String where = "ID = " + campeonato.getId();
        valores = new ContentValues();
        valores.put("NOME", campeonato.getNomeCampeonato());
        valores.put("PREMIACAO", campeonato.getPremiacao());
        valores.put("LOCAL", campeonato.getLocal());
        db.update(CriaBanco.TABELA_CAMPEOANTOS, valores,where,null);
        db.close();
        return retorno;
    }

    public CampeonatoBean buscarCampeonatoPorId(int campeonatoEnt){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CAMPEONATOS where id = " + campeonatoEnt, null);
        CampeonatoBean campeonato = new CampeonatoBean(0,"",null, "");
        if(cursor.moveToFirst()){
            campeonato.setId(cursor.getInt(0));
            campeonato.setNomeCampeonato(cursor.getString(1));
            campeonato.setPremiacao(cursor.getDouble(2));
            campeonato.setLocal(cursor.getString(3));
        }
        return campeonato;
    }

    public List<CampeonatoBean> listarCampeonatos() {
        List<CampeonatoBean> campeonatos = new ArrayList<CampeonatoBean>();
        String selectQuery = "SELECT * FROM CAMPEONATOS" ;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CampeonatoBean campeonato = new CampeonatoBean(0,"",null, "");
                campeonato.setId(cursor.getInt(0));
                campeonato.setNomeCampeonato(cursor.getString(1));
                campeonato.setPremiacao(cursor.getDouble(2));
                campeonato.setLocal(cursor.getString(3));
                campeonatos.add(campeonato);
            } while (cursor.moveToNext());
        }
        return campeonatos;
    }

    public List<CampeonatoBean> listarCampeonatosPorNome(CampeonatoBean campeonatoEnt) {
        List<CampeonatoBean> campeonatos = new ArrayList<CampeonatoBean>();
        String parametro = campeonatoEnt.getNomeCampeonato();
        String selectQuery = "SELECT * FROM CAMPEONATOS WHERE NOME LIKE ?" ;
        String[] whereArgs = new String[] { "%" + parametro + "%"  };
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, whereArgs);
        if (cursor.moveToFirst()) {
            do {
                CampeonatoBean campeonato = new CampeonatoBean(0,"",null, "");
                campeonato.setId(cursor.getInt(0));
                campeonato.setNomeCampeonato(cursor.getString(1));
                campeonato.setPremiacao(cursor.getDouble(2));
                campeonato.setLocal(cursor.getString(3));
                campeonatos.add(campeonato);
            } while (cursor.moveToNext());
        }
        return campeonatos;
    }
}
