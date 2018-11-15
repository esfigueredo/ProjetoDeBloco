package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PromocaoBO {

    PromocaoDAO promocadaodao =  new PromocaoDAO();

    public void Cadatrar(Promocao promocao){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        promocadaodao.cadastro(user.getUid(),promocao);
    }

}
