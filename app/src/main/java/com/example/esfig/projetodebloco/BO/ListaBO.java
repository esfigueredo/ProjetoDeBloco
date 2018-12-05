package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.ListaDAO;
import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListaBO {

    ListaDAO listadao =  new ListaDAO();

    public String Cadatrar(Lista lista) throws Exception {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(lista.getId() == null) {
            lista.setId(listadao.createTransactionID());
        }
        listadao.saveLista(user.getUid(),lista);
        return lista.getId();
    }

    public void addLista(Promocao promocao, String ListaID){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        listadao.addPromocao(user.getUid(),promocao,ListaID);
    }

    public void getTodasListas(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Lista.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }
    public void setEventiListenerLista(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        listadao.setEventiListener(Lista.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }

    public void setEventiListenerListaPromocao(FireBaseCalback fireBaseCalback,String listaID) throws IllegalAccessException, InstantiationException{
        listadao.setEventiListener(Lista.class, FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+listaID, "",fireBaseCalback);
    }
}
