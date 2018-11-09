package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComunsDAO {

    final FirebaseDatabase database;
    final DatabaseReference Refdatabase;

    public ComunsDAO(FirebaseDatabase database) {
        this.database = FirebaseDatabase.getInstance();

        Refdatabase = database.getReference();
    }

    public <T>T getObject(Class<T> oclass){
        //final T obj = oclass.newInstance();
        Refdatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return null;
    }

}
