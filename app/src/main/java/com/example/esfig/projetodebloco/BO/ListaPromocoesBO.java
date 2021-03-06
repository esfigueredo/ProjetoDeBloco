package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.ListaPromocoesDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.ListaPromocoes;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;

public class ListaPromocoesBO {

    ListaPromocoesDAO listaPromo =  new ListaPromocoesDAO();


    public void setEventiListenerPromo(FireBaseCalback fireBaseCalback,String ListID) throws IllegalAccessException, InstantiationException{
        listaPromo.getObject(ListaPromocoes.class, FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+ListID, "",fireBaseCalback);
    }

    public void remove(String listID,String promoID){
        listaPromo.remove(FirebaseAuth.getInstance().getCurrentUser().getUid(),listID,promoID);
    }
}
