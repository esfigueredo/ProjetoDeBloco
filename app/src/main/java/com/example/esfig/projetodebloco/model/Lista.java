package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Lista {

    public String id;
    public String nome;
    public String promocaoID;
    public String promocaoNome;


    public Lista() {
    }

    @Exclude
    public Promocao promocao;


    public String getPromocaoID() {
        return promocaoID;
    }

    public void setPromocaoID(String promocaoID) {
        this.promocaoID = promocaoID;
    }

    public String getPromocaoNome() {
        return promocaoNome;
    }

    public void setPromocaoNome(String promocaoNome) {
        this.promocaoNome = promocaoNome;
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

    @Exclude
    public Promocao getPromocao() {
        return promocao;
    }

    /**
     * precisa preencher a promoção com produto, marca antes e preço.
     * @param promocao
     */
    @Exclude
    public void setPromocao(Promocao promocao) {
        this.promocaoID = promocao.getId();
        this.promocaoNome = promocao.getProduto().getNome() + " " + promocao.getProduto().getMarca() + " " + promocao.getPreco();
        this.promocao = promocao;
    }
}
