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

import com.example.soccerapp2020.DBS.ControllerClube;
import com.example.soccerapp2020.Modelos.ClubeBean;
import com.example.soccerapp2020.R;

import java.util.List;

public class ListClubeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView ListaDeClubes;
    List<ClubeBean> clubes;
    Button pesquisaClube;
    ArrayAdapter<ClubeBean> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clube);
        final Context con = getBaseContext();
        final ControllerClube controllerClube = new ControllerClube(con);
        ListaDeClubes = (ListView) findViewById(R.id.listClubes);
        ListaDeClubes.setOnItemClickListener(this); // Clique no item
        ListaDeClubes.setOnItemLongClickListener(this); //

        //final EditText login = (EditText)findViewById(R.id.login);

        pesquisaClube = (Button) findViewById(R.id.btpesquisar);
        pesquisaClube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nome  = (EditText) findViewById(R.id.textEdit_nome);
                String nomeString = nome.getText().toString();
                ClubeBean clube = new ClubeBean(0,"","");
                clube.setNomeClube(nomeString);
                clubes = controllerClube.listarClubesPorNome(clube);
                adapter = new ArrayAdapter<ClubeBean>(con,android.R.layout.simple_list_item_1,clubes);
                ListaDeClubes.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi pressionado
        // position é a posição do item no adapter
        ClubeBean clube = (ClubeBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListClubeActivity.this, UpdateClubeActivity.class);
        it.putExtra("Clube",clube);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Pressionado :-" + position + " ID= " + clube.getId(),Toast.LENGTH_LONG).show();
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Código para trabalhar com o item que foi clicado
        // position é a posição do item no adapter
        ClubeBean clube = (ClubeBean) parent.getItemAtPosition(position);
        Intent it = new Intent(ListClubeActivity.this, UpdateClubeActivity.class);
        it.putExtra("Clube",clube);
        startActivity(it);
        Toast.makeText(getApplicationContext(),"Item Click :-" + position + " ID= " + clube.getId(),Toast.LENGTH_LONG).show();
    }
}
