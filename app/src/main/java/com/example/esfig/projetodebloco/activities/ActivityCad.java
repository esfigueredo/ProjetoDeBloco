package com.example.esfig.projetodebloco.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.AdapterView;

import com.example.esfig.projetodebloco.Adapters.MarcaAutocompleteAdapter;
import com.example.esfig.projetodebloco.Adapters.ProdutoAutocompleatAdapter;
import com.example.esfig.projetodebloco.DAO.ComunsDAO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Marca;
import com.example.esfig.projetodebloco.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ActivityCad extends AppCompatActivity {

    private AppCompatAutoCompleteTextView autoTextViewCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        /*List<Produto> lp = new ArrayList<>();
        Produto p = new Produto();
        p.setId("325");
        p.setIdMarca("555");
        p.setNome("banana");
        p.setNomeMarca("loreal");
        lp.add(p);
        Produto p1 = new Produto();
        p1.setId("325");
        p1.setIdMarca("555");
        p1.setNome("cenora");
        p1.setNomeMarca("loreal");
        lp.add(p1);*/


        ComunsDAO cdao = new ComunsDAO();
        //Context c = this;
        try {
            cdao.setEventiListener(Produto.class, "felipe", "", new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {
                    findViewById(R.id.LocalId);

                    List<Produto> lp = (ArrayList<Produto>) list;

                    autoTextViewCustom = (AppCompatAutoCompleteTextView) findViewById(R.id.ProdutoId);
                    autoTextViewCustom = (AppCompatAutoCompleteTextView) findViewById(R.id.marcadoprodutoId);

                    ProdutoAutocompleatAdapter produtoAdapter = new ProdutoAutocompleatAdapter(ActivityCad.this, R.layout.row_produto_autocompleate, lp);
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
            });

            cdao.setEventiListener(Marca.class, "felipe","", new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {
                    findViewById(R.id.LocalId);

                    List<Marca> lm = (ArrayList<Marca>)list;
                    autoTextViewCustom = (AppCompatAutoCompleteTextView) findViewById(R.id.marcadoprodutoId);

                    MarcaAutocompleteAdapter marcaAdapter = new MarcaAutocompleteAdapter(ActivityCad.this, R.layout.row_marca_autocomplete, lm);
                    autoTextViewCustom.setThreshold(1);
                    autoTextViewCustom.setAdapter(marcaAdapter);
                    // handle click event and set desc on textview
                    autoTextViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Marca marca = (Marca) adapterView.getItemAtPosition(i);
                            autoTextViewCustom.setText(marca.getMarca());
                        }
                    });
                }
            });

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
