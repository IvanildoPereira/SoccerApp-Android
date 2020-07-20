package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soccerapp2020.DBS.ControllerCampeonato;
import com.example.soccerapp2020.DBS.ControllerClube;
import com.example.soccerapp2020.DBS.ControllerClubeCampeonato;
import com.example.soccerapp2020.Modelos.CampeonatoBean;
import com.example.soccerapp2020.Modelos.ClubeBean;
import com.example.soccerapp2020.Modelos.ClubeCampeonatoBean;
import com.example.soccerapp2020.R;

import java.util.List;

public class CreateRelationActivity extends AppCompatActivity {
    ArrayAdapter<ClubeBean> adapterClube = null;
    ArrayAdapter<CampeonatoBean> adapterCampeonato = null;
    List<ClubeBean> clubes;
    List<CampeonatoBean> campeonatos;
    Spinner spinnerClube, spinnerCampeonato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_relation);
        Button Inserir = (Button) findViewById(R.id.btinserir);

        final Context con = getBaseContext();
        final ControllerClube controllerClube = new ControllerClube(con);
        final ControllerCampeonato controllerCampeonato = new ControllerCampeonato(con);
        final ControllerClubeCampeonato controllerClubeCampeonato = new ControllerClubeCampeonato(con);

        clubes = controllerClube.listarClubes();
        spinnerClube = findViewById(R.id.spinnerClube);
        adapterClube = new ArrayAdapter<ClubeBean>(con,android.R.layout.simple_spinner_item, clubes);
        adapterClube.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClube.setAdapter(adapterClube);


        campeonatos = controllerCampeonato.listarCampeonatos();
        spinnerCampeonato = findViewById(R.id.spinnerCampeonato);
        adapterCampeonato = new ArrayAdapter<CampeonatoBean>(con,android.R.layout.simple_spinner_item, campeonatos);
        adapterCampeonato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampeonato.setAdapter(adapterCampeonato);

        Inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClubeBean clube = (ClubeBean) spinnerClube.getSelectedItem();
                CampeonatoBean campeonato = (CampeonatoBean) spinnerCampeonato.getSelectedItem();
                EditText Observacao  = (EditText) findViewById(R.id.TxtEditObservacao);
                String observacaoString = Observacao.getText().toString();

                ClubeCampeonatoBean clubeCampeonatoBean = new ClubeCampeonatoBean(0,clube.getId(),campeonato.getId(),observacaoString);
                String msg = controllerClubeCampeonato.inserir(clubeCampeonatoBean);
                //String msg = clube.getId() + " " + campeonato.getId();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(CreateRelationActivity.this, MenuPrincipal.class);
                startActivity(it);

            }
        });
    }


}
