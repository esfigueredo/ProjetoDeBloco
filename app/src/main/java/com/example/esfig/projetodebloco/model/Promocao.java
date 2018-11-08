package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Promocao implements Serializable {

    public String id;
    public Produto produto;
    public String nomeMarca;
    public double preco;

    public String nomeLocal;
    public String idLocal;


    @Exclude
    public Local localPromo;


    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Local getLocalPromo() {
        return localPromo;
    }

    public void setLocalPromo(Local localPromo) {
        this.idLocal = localPromo.getId();
        this.nomeLocal = localPromo.getNome();
        this.localPromo = localPromo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
