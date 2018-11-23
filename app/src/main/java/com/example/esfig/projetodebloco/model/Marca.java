package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Marca implements Serializable {

    public String id;
    public String marcanome;

    public Marca() {
    }

    public String getMarca() {
        return marcanome;
    }

    public void setMarca(String marca) {
        marcanome = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
