package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.UsuarioDao;
import com.example.esfig.projetodebloco.Util.Config;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UsuarioBo {

    public void add(final Usuario use) {

        UsuarioDao udao =  new UsuarioDao();
        ListaBO lbo = new ListaBO();

        udao.getUsuario(new FireBaseCalback() {
            @Override
            public <T> void onCalback(List<T> list) {
                try {
                    Lista l = new Lista();
                    if (list.size() < 1) {
                        l.setNome("lista Default");

                        Config.ContantList = lbo.Cadatrar(l);
                        use.setIdCorrentList(Config.ContantList);
                        udao.add(use);
                    }else{
                       Usuario nuse =  ((List<Usuario>) list).get(0);
                       Config.ContantList = use.getIdCorrentList();
                        udao.add(nuse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, use.getId());

    }
}
