package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Lista {

    public String id;
    public String nome;


    public Lista() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * precisa preencher a promoção com produto, marca antes e preço.
     * @param promocao
     */
}
