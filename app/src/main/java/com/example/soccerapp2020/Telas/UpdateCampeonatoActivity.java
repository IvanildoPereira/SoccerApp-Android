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

public class UpdateCampeonatoActivity extends AppCompatActivity {
    Button updateCampeonato, deleteCampeonato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_campeonato);
        final ControllerCampeonato controllerCampeonato = new ControllerCampeonato(getBaseContext());
        final EditText nome = (EditText)findViewById(R.id.TxtEditNomeCampeonato);
        final EditText premiacao = (EditText)findViewById((R.id.TxtEditPremiacao));
        final EditText local = (EditText)findViewById((R.id.TxtEditLocal));
        Intent it = getIntent();
        final CampeonatoBean recuperado = (CampeonatoBean) it.getSerializableExtra("Campeonato");
        nome.setText(recuperado.getNomeCampeonato());
        premiacao.setText(recuperado.getPremiacao().toString());
        local.setText(recuperado.getLocal());

        updateCampeonato = (Button) findViewById(R.id.btalterar);
        updateCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nomeString = nome.getText().toString();
                Double premiacaoDouble = Double.parseDouble(premiacao.getText().toString());
                String localString = local.getText().toString();
                recuperado.setNomeCampeonato(nomeString);
                recuperado.setPremiacao(premiacaoDouble);
                recuperado.setLocal(localString);
                String msg = controllerCampeonato.alterar(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateCampeonatoActivity.this, ListCampeonatoActivity.class);
                startActivity(it);
            }
        });

        deleteCampeonato = (Button) findViewById(R.id.btexcluir);
        deleteCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg = controllerCampeonato.excluir(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateCampeonatoActivity.this, ListCampeonatoActivity.class);
                startActivity(it);
            }
        });
    }
}
