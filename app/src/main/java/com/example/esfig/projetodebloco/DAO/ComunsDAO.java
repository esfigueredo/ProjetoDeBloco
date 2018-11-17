package com.example.esfig.projetodebloco.DAO;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComunsDAO {

    FirebaseDatabase database;
    DatabaseReference Refdatabase;
    private Object obj;

    public ComunsDAO() {
        if(database == null) {
            this.database = FirebaseDatabase.getInstance();
        }
        if(Refdatabase == null) {
            Refdatabase = database.getReference();
        }
    }

    public <T>T getObject(final Class<T> oclass) throws IllegalAccessException, InstantiationException {

        Refdatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                obj = dataSnapshot.getValue(oclass);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return oclass.cast(obj);
    }

}
