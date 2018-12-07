package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;

public class ListaPromocoesDAO extends ComunsDAO {

    public void remove(@NonNull final String Userid,String ListID, @NonNull final String promocaoID){
        Refdatabase.child("listapromocoes/"+Userid+"/"+ListID+"/"+promocaoID).removeValue();
    }

}
