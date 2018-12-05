package com.example.esfig.projetodebloco.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Usuario implements Serializable{

    public String email;
    public String id;
    public String idCorrentList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String uid) {
        this.id = uid;
    }
    public String getIdCorrentList() {
        return idCorrentList;
    }

    public void setIdCorrentList(String idCorrentList) {
        this.idCorrentList = idCorrentList;
    }
}
