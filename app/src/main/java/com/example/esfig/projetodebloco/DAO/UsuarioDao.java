package com.example.esfig.projetodebloco.DAO;

import com.example.esfig.projetodebloco.BO.ListaBO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Usuario;

public class UsuarioDao extends  ComunsDAO {

    public void add(Usuario user){
        Refdatabase.child(user.getClass().getSimpleName().toLowerCase()+"/ADM/"+user.getId()).setValue(user);
    }

    public void getUsuario(FireBaseCalback calback, String ID){
        try {
            getObject(Usuario.class,"ADM",ID,calback);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
