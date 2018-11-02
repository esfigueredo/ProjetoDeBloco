package com.example.esfig.projetodebloco.model;

import java.io.Serializable;

public class Promocao implements Serializable {

    public String id;
    public Produto produto;
    public String nomeMarca;
    public double preco;
    public Local localPromo;

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
        this.localPromo = localPromo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
