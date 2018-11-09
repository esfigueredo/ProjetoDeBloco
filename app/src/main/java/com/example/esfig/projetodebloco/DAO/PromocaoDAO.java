package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class PromocaoDAO extends ComunsDAO{

    public void cadastro(@NonNull final String Userid, @NonNull final Promocao promocao){

        //database.getReference().child("promocao/"+Userid+"/"+promocao.getId()).setValue(promocao);

        //database.getReference().setValue("Hello, World!");

        
    }

}
