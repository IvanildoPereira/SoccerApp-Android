package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soccerapp2020.DBS.ControllerClube;
import com.example.soccerapp2020.Modelos.ClubeBean;
import com.example.soccerapp2020.R;

public class UpdateClubeActivity extends AppCompatActivity {
    Button updateClube, deleteClube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_clube);
        final ControllerClube controllerClube = new ControllerClube(getBaseContext());
        final EditText nome = (EditText)findViewById(R.id.TxtEditNomeClube);
        final EditText anoFundacao = (EditText)findViewById((R.id.TxtEditAnoFundacao));
        Intent it = getIntent();
        final ClubeBean recuperado = (ClubeBean) it.getSerializableExtra("Clube");
        nome.setText(recuperado.getNomeClube());
        anoFundacao.setText(recuperado.getAnoFundacao());


        updateClube = (Button) findViewById(R.id.btalterar);
        updateClube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nomeString = nome.getText().toString();
                String anoFundacaoString = anoFundacao.getText().toString();
                recuperado.setNomeClube(nomeString);
                recuperado.setAnoFundacao(anoFundacaoString);
                String msg = controllerClube.alterar(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateClubeActivity.this, ListClubeActivity.class);
                startActivity(it);
            }
        });

        deleteClube = (Button) findViewById(R.id.btexcluir);
        deleteClube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg = controllerClube.excluir(recuperado);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(UpdateClubeActivity.this, ListClubeActivity.class);
                startActivity(it);
            }
        });
    }
}
