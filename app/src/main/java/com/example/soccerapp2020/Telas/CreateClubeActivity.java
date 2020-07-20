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

public class CreateClubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_clube);
        final ControllerClube controllerClube = new ControllerClube(getBaseContext());
        Button Inserir = (Button) findViewById(R.id.btinserir);

        Inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome  = (EditText) findViewById(R.id.TxtEditNomeClube);
                EditText anoFundacao  = (EditText) findViewById((R.id.TxtEditAnoFundacao));

                String nomeString = nome.getText().toString();
                String anoFundacaoString = anoFundacao.getText().toString();


                ClubeBean clube = new ClubeBean(0,"","");
                clube.setId(0);
                clube.setNomeClube(nomeString);
                clube.setAnoFundacao(anoFundacaoString);
                String msg = controllerClube.inserir(clube);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent it = new Intent(CreateClubeActivity.this, MenuPrincipal.class);
                startActivity(it);
            }
        });
    }
}
