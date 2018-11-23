package com.example.esfig.projetodebloco.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ActivityCad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        p.setId("325");
        p.setIdMarca("555");
        p.setNome("banana");
        p.setNomeMarca("loreal");
        lp.add(p);
        Produto p1 = new Produto();
        p1.setId("325");
        p1.setIdMarca("555");
        p1.setNome("banana");
        p1.setNomeMarca("loreal");
        lp.add(p1);




    }

    View.OnClickListener saveClick = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

        }
    };



}
