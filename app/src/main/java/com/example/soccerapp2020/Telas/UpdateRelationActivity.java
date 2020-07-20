package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class UpdateRelationActivity extends AppCompatActivity {
    Button updateClubeCampeonato, deleteClubeCampeonato;
    ArrayAdapter<ClubeBean> adapterClube = null;
    ArrayAdapter<CampeonatoBean> adapterCampeonato = null;
    List<ClubeBean> clubes;
    List<CampeonatoBean> campeonatos;
    Spinner spinnerClube, spinnerCampeonato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_relation);
        final ControllerClubeCampeonato controllerClubeCampeonato = new ControllerClubeCampeonato(getBaseContext());
        final ControllerClube controllerClube = new ControllerClube(getBaseContext());
        final ControllerCampeonato controllerCampeonato = new ControllerCampeonato(getBaseContext());
        final EditText observacao = (EditText)findViewById(R.id.TxtEditObservacaoUpdate);
        Intent it = getIntent();
        final ClubeCampeonatoBean recuperado = (ClubeCampeonatoBean) it.getSerializableExtra("ClubeCampeonato");
        observacao.setText(recuperado.getDescricao());

        clubes = controllerClube.listarClubes();
        spinnerClube = findViewById(R.id.spinnerClube);
        adapterClube = new ArrayAdapter<ClubeBean>(getBaseContext(),android.R.layout.simple_spinner_item, clubes);
        adapterClube.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClube.setAdapter(adapterClube);
        int clubeIndex = 0;
        for(ClubeBean clube : clubes){

            if(clube.getId() == recuperado.getIdClube()){
                spinnerClube.setSelection(clubeIndex);
            }
            clubeIndex += 1;
        }



        campeonatos = controllerCampeonato.listarCampeonatos();
        spinnerCampeonato = findViewById(R.id.spinnerCampeonato);
        adapterCampeonato = new ArrayAdapter<CampeonatoBean>(getBaseContext(),android.R.layout.simple_spinner_item, campeonatos);
        adapterCampeonato.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampeonato.setAdapter(adapterCampeonato);
        int campeonatoIndex = 0;
        for(CampeonatoBean campeonato : campeonatos){

            if(campeonato.getId() == recuperado.getIdCampeonato()){
                spinnerCampeonato.setSelection(campeonatoIndex);
            }
            campeonatoIndex += 1;
        }



        updateClubeCampeonato = (Button) findViewById(R.id.btalterar);
        updateClubeCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClubeBean clube = (ClubeBean) spinnerClube.getSelectedItem();
                CampeonatoBean campeonato = (CampeonatoBean) spinnerCampeonato.getSelectedItem();
                String observacaoString = observacao.getText().toString();
                Log.d("Clube Id: ", " "+clube.getId());
                recuperado.setIdClube(clube.getId());
                recuperado.setIdCampeonato(campeonato.getId());
                recuperado.setDescricao(observacaoString);
                String msg = controllerClubeCampeonato.alterar(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateRelationActivity.this, ListRelationActivity.class);
                startActivity(it);
            }
        });

        deleteClubeCampeonato = (Button) findViewById(R.id.btexcluir);
        deleteClubeCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg = controllerClubeCampeonato.excluir(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateRelationActivity.this, ListRelationActivity.class);
                startActivity(it);
            }
        });
    }
}
