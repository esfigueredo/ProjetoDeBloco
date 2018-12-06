package com.example.esfig.projetodebloco.DAO;

import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.ListaPromocoes;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Promocao;

public class ListaDAO extends ComunsDAO{

    public ListaDAO() {
        super();
    }

    public void saveLista(String UserID, Lista lista){
        Refdatabase.child(lista.getClass().getSimpleName().toLowerCase()+"/"+UserID+"/"+lista.getId()).setValue(lista);
    }

    public void  addPromocao(String UserID, Promocao promocao,String ListID){
        Refdatabase.child(ListaPromocoes.class.getSimpleName().toLowerCase() +"/"+UserID+"/"+ListID+"/"+promocao.getId()).setValue(promocao);
    }

}
