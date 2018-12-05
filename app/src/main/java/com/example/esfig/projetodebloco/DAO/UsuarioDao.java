package com.example.esfig.projetodebloco.DAO;

import com.example.esfig.projetodebloco.BO.ListaBO;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Usuario;

public class UsuarioDao extends  ComunsDAO {

    public void add(Usuario user){
        Refdatabase.child(user.getClass().getSimpleName().toLowerCase()+"/ADM/"+user.getId()).setValue(user);
    }

}
