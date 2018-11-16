package com.example.esfig.projetodebloco.telasiniciais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esfig.projetodebloco.BO.PromocaoBO;
import com.example.esfig.projetodebloco.DAO.ComunsDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Marca;
import com.example.esfig.projetodebloco.model.Produto;
import com.example.esfig.projetodebloco.model.Promocao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PromocaoDAO pmbo = new PromocaoDAO();
        Promocao p = new Promocao();
        /*Produto p1 = new Produto();
        Marca m = new Marca();
        Local l = new Local();

        m.setId("aeee");
        m.setMarca("purina");

        p1.setId("asdw");
        p1.setNome("pao");
        p1.setMarca(m);

        l.setId("SSE");
        l.setNome("carefur");
        l.setEndereco("barra av atlantica 55");

        p.setId("lote2");
        p.setPreco(0.54);
        p.setProduto(p1);
        p.setLocalPromo(l);

        pmbo.cadastroteste("felipe",p);*/

        ComunsDAO  cdao = new ComunsDAO();
        try {
            Promocao object = cdao.getObject(Promocao.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        ComunsDAO  cdao2 = new ComunsDAO();

    }
}