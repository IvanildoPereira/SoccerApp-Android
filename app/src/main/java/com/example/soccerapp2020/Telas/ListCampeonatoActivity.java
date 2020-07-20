package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soccerapp2020.DBS.ControllerCampeonato;
import com.example.soccerapp2020.Modelos.CampeonatoBean;
import com.example.soccerapp2020.R;

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

import java.util.List;

public class ListCampeonatoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView ListaDeCampeonatos;
    List<CampeonatoBean> campeonatos;
    Button pesquisaCampeonatos;
    ArrayAdapter<CampeonatoBean> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_campeonato);
        final Context con = getBaseContext();
        final ControllerCampeonato controllerCampeonato = new ControllerCampeonato(con);
        ListaDeCampeonatos = (ListView) findViewById(R.id.listCampeonatos);
        ListaDeCampeonatos.setOnItemClickListener(this); // Clique no item
        ListaDeCampeonatos.setOnItemLongClickListener(this); //


        //final EditText login = (EditText)findViewById(R.id.login);

        pesquisaCampeonatos = (Button) findViewById(R.id.btpesquisar);
        pesquisaCampeonatos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nome  = (EditText) findViewById(R.id.textEdit_nome);
                String nomeString = nome.getText().toString();
                CampeonatoBean campeonato = new CampeonatoBean(0,"",null,"");
                campeonato.setNomeCampeonato(nomeString);
                campeonatos = controllerCampeonato.listarCampeonatosPorNome(campeonato);
                adapter = new ArrayAdapter<CampeonatoBean>(con,android.R.layout.simple_list_item_1,campeonatos);
                ListaDeCampeonatos.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi pressionado
        // position é a posição do item no adapter
        CampeonatoBean campeonato = (CampeonatoBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListCampeonatoActivity.this, UpdateCampeonatoActivity.class);
        it.putExtra("Campeonato",campeonato);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Pressionado :-" + position + " ID= " + campeonato.getId(),Toast.LENGTH_LONG).show();
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi clicado
        // position é a posição do item no adapter
        CampeonatoBean campeonato = (CampeonatoBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListCampeonatoActivity.this, UpdateCampeonatoActivity.class);
        it.putExtra("Campeonato",campeonato);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Click :-" + position + " ID= " + campeonato.getId(),Toast.LENGTH_LONG).show();
    }
}
