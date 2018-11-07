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

    public void cadastrar(@NonNull final String Userid, @NonNull final Promocao promocao){

        database.getReference().runTransaction(new Transaction.Handler() {

            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {

                MarcaDAO marcadao =  new MarcaDAO();

                marcadao.AddMarca(mutableData,Userid,promocao.getProduto().getMarca());

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Log.d("marcaDao", "postTransaction:onComplete:" + databaseError);
            }
        });
    }

}
