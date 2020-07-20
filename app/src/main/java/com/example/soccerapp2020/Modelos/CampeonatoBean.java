package com.example.soccerapp2020.Modelos;

import java.io.Serializable;

public class CampeonatoBean implements Serializable {
    private int id;
    private String nomeCampeonato;
    private Double premiacao;
    private String local;

    public CampeonatoBean(int id, String nomeCampeonato, Double premiacao, String local) {
        this.id = id;
        this.nomeCampeonato = nomeCampeonato;
        this.premiacao = premiacao;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public Double getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(Double premiacao) {
        this.premiacao = premiacao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "ID: "+ id + ", Nome: " +nomeCampeonato + ", Premiação: R$"+premiacao+ ", Local: "+ local;
    }
}
