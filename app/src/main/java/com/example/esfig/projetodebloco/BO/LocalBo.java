package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LocalBo {

    public void getLocal(String localid, FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Local.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), localid,fireBaseCalback);
    }
}
