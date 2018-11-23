package com.example.esfig.projetodebloco.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.AdapterView;

import com.example.esfig.projetodebloco.Adapters.ProdutoAutocompleatAdapter;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ActivityCad extends AppCompatActivity {

    private AppCompatAutoCompleteTextView autoTextViewCustom;


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


        autoTextViewCustom = (AppCompatAutoCompleteTextView) findViewById(R.id.ProdutoId);

        ProdutoAutocompleatAdapter produtoAdapter = new ProdutoAutocompleatAdapter(this, R.layout.row_produto_autocompleate, lp);
        autoTextViewCustom.setThreshold(1);
        autoTextViewCustom.setAdapter(produtoAdapter);
// handle click event and set desc on textview
        autoTextViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto produto = (Produto) adapterView.getItemAtPosition(i);
                autoTextViewCustom.setText(produto.getNome());
            }
        });

    }

    View.OnClickListener saveClick = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

        }
    };



}
