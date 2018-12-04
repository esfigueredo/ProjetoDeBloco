package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PromocaoBO {

    PromocaoDAO promocadaodao =  new PromocaoDAO();

    public void Cadatrar(Promocao promocao){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        promocadaodao.cadastro(user.getUid(),promocao);
    }

    public void getTodasPromo(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Promocao.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }
    
    public void setEventiListenerPromo(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        promocadaodao.setEventiListener(Promocao.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }
   
}
