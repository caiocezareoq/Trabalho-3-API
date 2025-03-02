package com.sd.locacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private List<Aparelho> aparelhosAlugados = new ArrayList<>();

    // Construtores, getters e setters
    public Cliente() {}
    
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Aparelho> getAparelhosAlugados() {
        return aparelhosAlugados;
    }

    public void setAparelhosAlugados(List<Aparelho> aparelhosAlugados) {
        this.aparelhosAlugados = aparelhosAlugados;
    }

    // Método para adicionar um aparelho alugado
    public void alugarAparelho(Aparelho aparelho) {
        this.aparelhosAlugados.add(aparelho);
    }

}