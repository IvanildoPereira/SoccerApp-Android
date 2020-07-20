package com.example.soccerapp2020.Modelos;

import java.io.Serializable;

public class ClubeCampeonatoBean implements Serializable {
    private int id;
    private int idClube;
    private int idCampeonato;
    private String descricao;
    private ClubeBean clube;
    private CampeonatoBean campeonato;

    public ClubeCampeonatoBean(int id, int idClube, int idCampeonato, String descricao) {
        this.id = id;
        this.idClube = idClube;
        this.idCampeonato = idCampeonato;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClube() {
        return idClube;
    }

    public void setIdClube(int idClube) {
        this.idClube = idClube;
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ClubeBean getClube() {
        return clube;
    }

    public void setClube(ClubeBean clube) {
        this.clube = clube;
    }

    public CampeonatoBean getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(CampeonatoBean campeonato) {
        this.campeonato = campeonato;
    }

    @Override
    public String toString() {
        return "Nome: " + clube.getNomeClube() + "\nCampeonato: " + campeonato.getNomeCampeonato() +  "\nDescricao: " + descricao;
    }

}
