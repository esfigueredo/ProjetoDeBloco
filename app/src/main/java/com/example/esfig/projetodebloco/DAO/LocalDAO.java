package com.example.esfig.projetodebloco.DAO;

import com.example.esfig.projetodebloco.model.Local;

public class LocalDAO extends ComunsDAO{

    public LocalDAO() {
        super();
    }

    public void saveLocal(String UserID, Local local){
        Refdatabase.child(local.getClass().getSimpleName().toLowerCase()+"/"+UserID+"/"+local.getId()).setValue(local);
    }

}
