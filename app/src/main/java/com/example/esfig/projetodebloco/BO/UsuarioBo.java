package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.UsuarioDao;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Usuario;

public class UsuarioBo {

    public void add(Usuario use) {
        UsuarioDao udao =  new UsuarioDao();
        ListaBO lbo = new ListaBO();
        Lista l =  new Lista();
        l.setNome("lista Default");
        try {
            use.setIdCorrentList(lbo.Cadatrar(l));
        } catch (Exception e) {
            e.printStackTrace();
        }
        udao.add(use);
    }
}
