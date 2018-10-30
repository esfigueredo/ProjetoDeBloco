package com.example.esfig.projetodebloco.model;

import java.io.Serializable;

public class Marca implements Serializable {

    public String id;
    public String Marca;


    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
