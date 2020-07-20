package com.example.soccerapp2020.Modelos;

import java.io.Serializable;

public class ClubeBean implements Serializable {
    private int id;
    private String nomeClube;
    private String anoFundacao;

    public ClubeBean(int id, String nomeClube, String anoFundacao) {
        this.id = id;
        this.nomeClube = nomeClube;
        this.anoFundacao = anoFundacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public String getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(String anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    @Override
    public String toString() {
        return "ID: "+ id +", Nome: "+ nomeClube + ", Ano Fundação:"+ anoFundacao;
    }
}
