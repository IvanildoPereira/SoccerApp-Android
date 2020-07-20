package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.soccerapp2020.DBS.ControllerClubeCampeonato;
import com.example.soccerapp2020.Modelos.ClubeCampeonatoBean;
import com.example.soccerapp2020.R;

import java.util.List;

public class ListRelationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView ListaDeClubeCampeonato;
    List<ClubeCampeonatoBean> clubeCampeonatos;
    Button pesquisaClubeCampeonato;
    ArrayAdapter<ClubeCampeonatoBean> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_relation);
        final Context con = getBaseContext();
        final ControllerClubeCampeonato controllerClubeCampeonato = new ControllerClubeCampeonato(con);
        ListaDeClubeCampeonato = (ListView) findViewById(R.id.listClubeCampeoantos);
        ListaDeClubeCampeonato.setOnItemClickListener(this); // Clique no item
        ListaDeClubeCampeonato.setOnItemLongClickListener(this); //

        //final EditText login = (EditText)findViewById(R.id.login);

        pesquisaClubeCampeonato = (Button) findViewById(R.id.btpesquisar);
        pesquisaClubeCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText observacao  = (EditText) findViewById(R.id.textEdit_Observacao);
                String observacaoString = observacao.getText().toString();
                ClubeCampeonatoBean clubeCampeonato = new ClubeCampeonatoBean(0,0,0,"");
                clubeCampeonato.setDescricao(observacaoString);
                clubeCampeonatos = controllerClubeCampeonato.listarClubeCampeonatosPorObservacao(clubeCampeonato);
                adapter = new ArrayAdapter<ClubeCampeonatoBean>(con,android.R.layout.simple_list_item_1,clubeCampeonatos);
                ListaDeClubeCampeonato.setAdapter(adapter);
            }
        });
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi pressionado
        // position é a posição do item no adapter
        ClubeCampeonatoBean clubeCampeonato = (ClubeCampeonatoBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListRelationActivity.this, UpdateRelationActivity.class);
        it.putExtra("ClubeCampeonato",clubeCampeonato);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Pressionado :-" + position + " ID= " + clubeCampeonato.getId(),Toast.LENGTH_LONG).show();
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi clicado
        // position é a posição do item no adapter
        ClubeCampeonatoBean clubeCampeonato = (ClubeCampeonatoBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListRelationActivity.this, UpdateRelationActivity.class);
        it.putExtra("ClubeCampeonato",clubeCampeonato);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Click :-" + position + " ID= " + clubeCampeonato.getId(), Toast.LENGTH_LONG).show();
    }
}
