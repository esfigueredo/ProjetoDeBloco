package com.example.esfig.projetodebloco.DAO;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    public <T> void getObject(final Class<T> oclass, final String UsertID, final String key,final FireBaseCalback fireBaseCalback)
            throws IllegalAccessException, InstantiationException {
        final List<T> list = new ArrayList<T>();

        //DatabaseReference thisref = Refdatabase.child(oclass.getSimpleName().toLowerCase()+"/UsertID");
        Refdatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot el = dataSnapshot.child(oclass.getSimpleName().toLowerCase() + "/" + UsertID);

                for (DataSnapshot childSnapshot: el.getChildren()) {
                    obj = childSnapshot.getValue(oclass);
                    try {
                        Method methodmethod = oclass.getMethod("getId",null);
                        try {
                            if(key.isEmpty() || ((String)methodmethod.invoke(obj)).equals(key)) {
                                list.add(oclass.cast(obj));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }

                fireBaseCalback.onCalback(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("erro Database",databaseError.getMessage());
            }
        });
    }

}
