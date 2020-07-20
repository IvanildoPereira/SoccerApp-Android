package com.example.soccerapp2020.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.soccerapp2020.R;

public class MenuPrincipal extends AppCompatActivity {
    Button createClube, listClube, createCampeonato, listCampeonato, createRelation, listRelation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        Intent it = getIntent();

        createClube = (Button) findViewById(R.id.btnCreateClube);
        createClube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, CreateClubeActivity.class);
                startActivity(it);
            }
        });

        listClube = (Button) findViewById(R.id.btnListClube);
        listClube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, ListClubeActivity.class);
                startActivity(it);
            }
        });

        createCampeonato = (Button) findViewById(R.id.btnCreateCampeonato);
        createCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, CreateCampeonatoActivity.class);
                startActivity(it);
            }
        });

        listCampeonato = (Button) findViewById(R.id.btnListCampeonato);
        listCampeonato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, ListCampeonatoActivity.class);
                startActivity(it);
            }
        });

        createRelation = (Button) findViewById(R.id.btnCreateRelation);
        createRelation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, CreateRelationActivity.class);
                startActivity(it);
            }
        });

        listRelation = (Button) findViewById(R.id.btnListRelation);
        listRelation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this, ListRelationActivity.class);
                startActivity(it);
            }
        });
    }
}
