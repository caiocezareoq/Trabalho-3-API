package com.sd.locacao;

import java.io.Serializable;

public class Aparelho implements Serializable {
    private String nome;
    private double precoLocacao;

    // Construtores, getters e setters
    public Aparelho() {}
    
    public Aparelho(String nome, double precoLocacao) {
        this.nome = nome;
        this.precoLocacao = precoLocacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(double precoLocacao) {
        this.precoLocacao = precoLocacao;
    }
}