package com.example.party.BancoDeDados;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class Pessoa implements Serializable {

    //Atributos da Classe
    private Integer id;
    private String nome;
    private String idade;

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() { return idade; }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome;
    }
}
