package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.esfig.projetodebloco.model.Marca;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class MarcaDAO extends ComunsDAO{


    public MarcaDAO() {
        super();
    }

    public void saveMarca(String UserID, Marca marca){
        Refdatabase.child(marca.getClass().getSimpleName().toLowerCase()+"/"+UserID+"/"+marca.getId()).setValue(marca);
    }

}
