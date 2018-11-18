package com.example.esfig.projetodebloco.DAO;

import com.example.esfig.projetodebloco.model.Produto;

public class ProdutoDAO extends ComunsDAO{

    public ProdutoDAO() {
        super();
    }

    public void saveProduto(String UserID, Produto produto){
        Refdatabase.child(produto.getClass().getSimpleName().toLowerCase()+"/"+UserID+"/"+produto.getId()).setValue(produto);
    }



}
