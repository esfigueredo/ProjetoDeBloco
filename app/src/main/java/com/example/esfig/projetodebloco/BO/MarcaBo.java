package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Marca;
import com.google.firebase.auth.FirebaseAuth;

public class MarcaBo {

    public void getTodasMarcas(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Marca.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }

}
