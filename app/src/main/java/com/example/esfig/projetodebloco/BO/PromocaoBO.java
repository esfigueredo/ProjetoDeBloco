package com.example.esfig.projetodebloco.BO;

import com.example.esfig.projetodebloco.DAO.LocalDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PromocaoBO {

    PromocaoDAO promocadaodao =  new PromocaoDAO();

    public void add(Promocao promocao){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        promocadaodao.cadastro(user.getUid(),promocao);
    }

    public void getTodasPromo(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        LocalDAO localdao =  new LocalDAO();
        localdao.getObject(Promocao.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }
    
    public void setEventiListenerPromo(FireBaseCalback fireBaseCalback) throws IllegalAccessException, InstantiationException{
        promocadaodao.setEventiListener(Promocao.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), "",fireBaseCalback);
    }

    public void getPromo(FireBaseCalback fireBaseCalback, String id) throws IllegalAccessException, InstantiationException{
        promocadaodao.getObject(Promocao.class, FirebaseAuth.getInstance().getCurrentUser().getUid(), id,fireBaseCalback);
    }

    public void Cadatrar(Promocao promocao) throws Exception {
        String id ;
        if(promocao.getProduto().getId() == null){
            id = promocadaodao.createTransactionID();
            promocao.setProdutoID(id);
            promocao.getProduto().setId(id);
        }
        if(promocao.getLocalPromo().getId() == null){
            id = promocadaodao.createTransactionID();
            promocao.setIdLocal(id);
            promocao.getLocalPromo().setId(id);
        }
        if(promocao.getProduto().getMarca().getId() == null){
            id = promocadaodao.createTransactionID();
            promocao.getProduto().setIdMarca(id);
            promocao.getProduto().getMarca().setId(promocadaodao.createTransactionID());
        }
        if(promocao.getId() == null){
            promocao.setId(promocadaodao.createTransactionID());
        }
        promocadaodao.cadastro(FirebaseAuth.getInstance().getCurrentUser().getUid(),promocao);
    }
}
