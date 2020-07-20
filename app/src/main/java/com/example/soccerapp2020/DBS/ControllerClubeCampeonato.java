package com.example.soccerapp2020.DBS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.soccerapp2020.Modelos.CampeonatoBean;
import com.example.soccerapp2020.Modelos.ClubeBean;
import com.example.soccerapp2020.Modelos.ClubeCampeonatoBean;

import java.util.ArrayList;
import java.util.List;

public class ControllerClubeCampeonato {
    private static CriaBanco dbHelper = null;
    private static ControllerClube controllerClube = null;
    private static ControllerCampeonato controllerCampeonato = null;
    private static SQLiteDatabase db = null;

    public ControllerClubeCampeonato(Context context) {
        if (dbHelper == null ) {
            dbHelper = new CriaBanco(context);
        }
        controllerClube = new ControllerClube(context);
        controllerCampeonato = new ControllerCampeonato(context);

    }

    public String inserir(ClubeCampeonatoBean clubeCampeoanto) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        long resultado;
        String retorno;
        valores = new ContentValues();
        valores.put("ID_CLUBE", clubeCampeoanto.getIdClube());
        valores.put("ID_CAMPEOANTO", clubeCampeoanto.getIdCampeonato());
        valores.put("OBS", clubeCampeoanto.getDescricao());
        resultado = db.insert(CriaBanco.TABELA_CLUBECAMPEOANTO, null, valores);
        db.close();

        if (resultado == -1) {
            retorno = "Erro ao inserir registro";
        } else {
            retorno = "Registro Inserido com sucesso";
        }
        return retorno;
    }

    public String excluir(ClubeCampeonatoBean clubeCampeoanto) {
        String retorno = "Resgistro Excluido com Sucesso";
        String where = "ID = " + clubeCampeoanto.getId();
        db = dbHelper.getReadableDatabase();
        db.delete(CriaBanco.TABELA_CLUBECAMPEOANTO,where,null);
        db.close();
        return retorno;
    }

    public String alterar(ClubeCampeonatoBean clubeCampeoanto) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores;
        String retorno = "Registro Alterado com sucesso";
        String where = "ID = " + clubeCampeoanto.getId();
        valores = new ContentValues();
        valores.put("ID_CLUBE", clubeCampeoanto.getIdClube());
        valores.put("ID_CAMPEOANTO", clubeCampeoanto.getIdCampeonato());
        valores.put("OBS", clubeCampeoanto.getDescricao());
        db.update(CriaBanco.TABELA_CLUBECAMPEOANTO, valores,where,null);
        db.close();
        return retorno;
    }

    public List<ClubeCampeonatoBean> listarClubeCampeonatos() {
        List<ClubeCampeonatoBean> relacao = new ArrayList<ClubeCampeonatoBean>();
        String selectQuery = "SELECT * FROM CLUBE_CAMPEONATO" ;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ClubeCampeonatoBean clubeCampeoanto = new ClubeCampeonatoBean(0,0,0,"");
                clubeCampeoanto.setId(cursor.getInt(0));
                clubeCampeoanto.setIdClube(cursor.getInt(1));
                clubeCampeoanto.setIdCampeonato(cursor.getInt(2));
                clubeCampeoanto.setDescricao(cursor.getString(3));
                relacao.add(clubeCampeoanto);
            } while (cursor.moveToNext());
        }

        for (ClubeCampeonatoBean listaFD : relacao) {
            ClubeBean clube = new ClubeBean(listaFD.getIdClube(),"","");
            CampeonatoBean campeonato = new CampeonatoBean(listaFD.getIdCampeonato(),"",null,"");
            listaFD.setClube(controllerClube.buscarClubePorId(listaFD.getIdClube()));
            listaFD.setCampeonato(controllerCampeonato.buscarCampeonatoPorId(listaFD.getIdCampeonato()));
        }

        return relacao;
    }

    public List<ClubeCampeonatoBean> listarClubeCampeonatosPorObservacao(ClubeCampeonatoBean clubeCampeonatoEnt) {
        List<ClubeCampeonatoBean> relacao = new ArrayList<ClubeCampeonatoBean>();
        String parametro = clubeCampeonatoEnt.getDescricao();
        String selectQuery = "SELECT * FROM CLUBE_CAMPEONATO WHERE OBS LIKE ?" ;
        String[] whereArgs = new String[] { "%" + parametro + "%"  };
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, whereArgs);
        if (cursor.moveToFirst()) {
            do {
                ClubeCampeonatoBean clubeCampeoanto = new ClubeCampeonatoBean(0,0,0,"");
                clubeCampeoanto.setId(cursor.getInt(0));
                clubeCampeoanto.setIdClube(cursor.getInt(1));
                clubeCampeoanto.setIdCampeonato(cursor.getInt(2));
                clubeCampeoanto.setDescricao(cursor.getString(3));
                relacao.add(clubeCampeoanto);
            } while (cursor.moveToNext());
        }

        for (ClubeCampeonatoBean listaFD : relacao) {
            //ClubeBean clube = new ClubeBean(listaFD.getIdClube(),"","");
            //CampeonatoBean campeonato = new CampeonatoBean(listaFD.getIdCampeonato(),"",null,"");
            listaFD.setClube(controllerClube.buscarClubePorId(listaFD.getIdClube()));
            Log.d("Clube Id =","IdClube" + listaFD.getClube().toString());
            listaFD.setCampeonato(controllerCampeonato.buscarCampeonatoPorId(listaFD.getIdCampeonato()));
        }



        return relacao;
    }
}
