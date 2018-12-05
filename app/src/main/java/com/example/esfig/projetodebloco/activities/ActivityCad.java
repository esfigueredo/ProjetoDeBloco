package com.example.esfig.projetodebloco.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.esfig.projetodebloco.Adapters.MarcaAutocompleteAdapter;
import com.example.esfig.projetodebloco.Adapters.ProdutoAutocompleatAdapter;
import com.example.esfig.projetodebloco.DAO.ComunsDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Marca;
import com.example.esfig.projetodebloco.model.Produto;
import com.example.esfig.projetodebloco.model.Promocao;

import java.util.ArrayList;
import java.util.List;

public class ActivityCad extends AppCompatActivity {


    private AppCompatAutoCompleteTextView autoTextViewCustomprod;
    private AppCompatAutoCompleteTextView autoTextViewCustommarca;

    Promocao promo = new Promocao();


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

                    List<Produto> lp = (ArrayList<Produto>) list;

                    autoTextViewCustomprod = (AppCompatAutoCompleteTextView) findViewById(R.id.ProdutoId);

                    ProdutoAutocompleatAdapter produtoAdapter = new ProdutoAutocompleatAdapter(ActivityCad.this, R.layout.row_produto_autocompleate, lp);
                    autoTextViewCustomprod.setThreshold(1);
                    autoTextViewCustomprod.setAdapter(produtoAdapter);
                    // handle click event and set desc on textview
                    autoTextViewCustomprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Produto produto = (Produto) adapterView.getItemAtPosition(i);
                            if (!promo.getProduto().getMarca().getMarca().isEmpty()) {
                                produto.setMarca(promo.getProduto().getMarca());
                            }
                            promo.setProduto(produto);
                            autoTextViewCustomprod.setText(produto.getNome());
                        }
                    });
                }
            });

            cdao.setEventiListener(Marca.class, "felipe", "", new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {

                    List<Marca> lm = (ArrayList<Marca>) list;
                    autoTextViewCustommarca = (AppCompatAutoCompleteTextView) findViewById(R.id.marcadoprodutoId);

                    MarcaAutocompleteAdapter marcaAdapter = new MarcaAutocompleteAdapter(ActivityCad.this, R.layout.row_marca_autocomplete, lm);
                    autoTextViewCustommarca.setThreshold(1);
                    autoTextViewCustommarca.setAdapter(marcaAdapter);
                    // handle click event and set desc on textview
                    autoTextViewCustommarca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Marca marca = (Marca) adapterView.getItemAtPosition(i);
                            if(promo.getProduto() == null){
                                promo.setProduto(new Produto());
                            }
                            promo.getProduto().setMarca(marca);
                            autoTextViewCustommarca.setText(marca.getMarca());
                        }
                    });


                }
            });

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.LocalId);
        // Get the string array
        String[] locais1 = getResources().getStringArray(R.array.Locais_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locais1);
        textView.setAdapter(adapter);

        findViewById(R.id.Savebtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String id = ((AutoCompleteTextView) findViewById(R.id.LocalId)).getText().toString() +((AutoCompleteTextView) findViewById(R.id.estabelecimentoId)).getText().toString();
                String nome = ((AutoCompleteTextView) findViewById(R.id.LocalId)).getText().toString();
                String end = ((AutoCompleteTextView) findViewById(R.id.estabelecimentoId)).getText().toString();
                Local l = new Local();
                l.setId(id);
                l.setNome(nome);
                l.setEndereco(end);
                promo.setLocalPromo(l);
                PromocaoDAO p = new PromocaoDAO();
                p.cadastro("felipe", promo);
            }
        });
    }
}



