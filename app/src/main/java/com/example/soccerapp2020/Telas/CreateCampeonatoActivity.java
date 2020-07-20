package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soccerapp2020.DBS.ControllerCampeonato;
import com.example.soccerapp2020.Modelos.CampeonatoBean;
import com.example.soccerapp2020.R;

public class CreateCampeonatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_campeonato);
        final ControllerCampeonato controllerCampeonato = new ControllerCampeonato(getBaseContext());
        Button Inserir = (Button) findViewById(R.id.btinserir);

        Inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome  = (EditText) findViewById(R.id.TxtEditNomeCampeonato);
                EditText premiacao  = (EditText) findViewById((R.id.TxtEditPremiacao));
                EditText local  = (EditText) findViewById((R.id.TxtEditLocal));

                String nomeString = nome.getText().toString();
                Double premiacaoDouble = Double.parseDouble(premiacao.getText().toString());
                String localString = local.getText().toString();

                CampeonatoBean campeonato = new CampeonatoBean(0,"",null,"");
                campeonato.setId(0);
                campeonato.setNomeCampeonato(nomeString);
                campeonato.setPremiacao(premiacaoDouble);
                campeonato.setLocal(localString);
                String msg = controllerCampeonato.inserir(campeonato);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(CreateCampeonatoActivity.this, MenuPrincipal.class);
                startActivity(it);
            }
        });
    }
}
