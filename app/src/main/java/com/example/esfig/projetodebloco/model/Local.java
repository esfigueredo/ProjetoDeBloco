package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Local {

    public String id;
    public String Endereco;
    public String nome;

    public Local() {
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
