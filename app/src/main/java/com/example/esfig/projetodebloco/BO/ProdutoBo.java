package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Produto;
import com.google.firebase.auth.FirebaseAuth;

public class ProdutoBo {

    public void getTodosProdutos(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Produto.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }
}
