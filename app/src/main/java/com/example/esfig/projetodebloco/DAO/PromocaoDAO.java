package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.database.FirebaseDatabase;

public class PromocaoDAO extends ComunsDAO{

    MarcaDAO marcadao =  new MarcaDAO();
    LocalDAO localdao = new LocalDAO();
    ProdutoDAO produtodao = new ProdutoDAO();

    public PromocaoDAO() {
        super();
    }

    public void cadastroteste(@NonNull final String Userid, @NonNull final Promocao promocao){
        Refdatabase.child("promocao/"+Userid+"/"+promocao.getId()).setValue(promocao);
    }

    public void cadastro(@NonNull final String Userid, @NonNull final Promocao promocao){

        produtodao.saveProduto(Userid,promocao.getProduto());
        marcadao.saveMarca(Userid,promocao.getProduto().getMarca());
        localdao.saveLocal(Userid,promocao.getLocalPromo());
        Refdatabase.child(promocao.getClass().getSimpleName().toLowerCase()+"/"+Userid+"/"+promocao.getId()).setValue(promocao);

    }

}
