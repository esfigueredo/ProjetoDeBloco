package com.example.esfig.projetodebloco.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.AdapterView;

import com.example.esfig.projetodebloco.Adapters.ProdutoAutocompleatAdapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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


        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ProdutoId);
        // Get the string array
        String[] produto = getResources().getStringArray(R.array.produto_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, produto);
        textView.setAdapter(adapter);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.marcadoprodutoId);
        // Get the string array
        String[] marcas = getResources().getStringArray(R.array.marca_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, marcas);
        textView1.setAdapter(adapter1);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.LocalId);
        // Get the string array
        String[] locais1 = getResources().getStringArray(R.array.Locais_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locais1);
        textView2.setAdapter(adapter2);
    }

    View.OnClickListener saveClick = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

        }
    };



}
