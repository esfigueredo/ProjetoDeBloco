package com.example.esfig.projetodebloco.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.esfig.projetodebloco.Adapters.MarcaAutocompleteAdapter;
import com.example.esfig.projetodebloco.Adapters.ProdutoAutocompleatAdapter;
import com.example.esfig.projetodebloco.BO.PromocaoBO;
import com.example.esfig.projetodebloco.DAO.ComunsDAO;
import com.example.esfig.projetodebloco.DAO.PromocaoDAO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.Util.UtilMask;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Marca;
import com.example.esfig.projetodebloco.model.Produto;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ActivityCad extends AppCompatActivity {


    private AppCompatAutoCompleteTextView autoTextViewCustomprod;
    private AppCompatAutoCompleteTextView autoTextViewCustommarca;

    Promocao promo = new Promocao();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        firebaseUser = firebaseAuth.getCurrentUser();

        ComunsDAO cdao = new ComunsDAO();
        //Context c = this;
        try {
            cdao.setEventiListener(Produto.class, firebaseUser.getUid(), "", new FireBaseCalback() {
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
                            produto.setMarca(promo.getProduto().getMarca());
                            promo.setProduto(produto);
                            autoTextViewCustomprod.setText(produto.getNome());
                        }
                    });
                }
            });

            cdao.setEventiListener(Marca.class, firebaseUser.getUid(), "", new FireBaseCalback() {
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
                try {

                String NomeLocal = ((AutoCompleteTextView) findViewById(R.id.LocalId)).getText().toString() +" "+((AutoCompleteTextView) findViewById(R.id.estabelecimentoId)).getText().toString();
                String nome = ((AutoCompleteTextView) findViewById(R.id.LocalId)).getText().toString();
                String end = ((AutoCompleteTextView) findViewById(R.id.estabelecimentoId)).getText().toString();
                String preco = ((EditText) findViewById(R.id.valordoprodutoId)).getText().toString();


                ((EditText) findViewById(R.id.valordoprodutoId)).addTextChangedListener(UtilMask.mask(((EditText) findViewById(R.id.valordoprodutoId)),UtilMask.FORMAT_PRECO));

                ((EditText) findViewById(R.id.valordoprodutoId)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        ((AutoCompleteTextView)v).setText(((AutoCompleteTextView)v).getText().toString().contains(",0")?((AutoCompleteTextView)v).getText(): ((AutoCompleteTextView)v).getText().append(",00"));
                    }
                });

                if(promo.getProdutoNome() == null){
                    Produto p =  new Produto();
                    p.setNome(((AutoCompleteTextView) findViewById(R.id.ProdutoId)).getText().toString());
                    promo.setProduto(p);
                }
                if(promo.getProduto().getMarca().getMarca() == null){
                    Marca m = new Marca();
                    m.setMarca(((AutoCompleteTextView) findViewById(R.id.marcadoprodutoId)).getText().toString());
                    promo.setNomeMarca(m.getMarca());
                    promo.getProduto().setMarca(m);
                }

                Local l = new Local();
                l.setNome(nome);
                l.setEndereco(end);
                promo.setLocalPromo(l);
                promo.setPreco(Double.valueOf(preco.replace(",",".")));
                promo.setNomeLocal(NomeLocal);

                PromocaoBO pbo =  new PromocaoBO();

                if(promo.getProdutoNome() == null || promo.getProdutoNome().isEmpty()){
                    throw new Exception("Produto Não informado");
                }
                if(promo.getPreco() == 0 || String.valueOf(promo.getPreco()).isEmpty()){
                    throw new Exception("Preço Não informado");
                }
                if(promo.getNomeLocal() == null || promo.getNomeLocal().isEmpty()){
                    throw new Exception("Local Não informado");
                }

                pbo.Cadatrar(promo);
                clear();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.Telacadastro), "Promoção cadastrada com sucesso.", Snackbar.LENGTH_LONG);
                snackbar.show();
                } catch (Exception e) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.Telacadastro), e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
    public void clear() {
       ((AutoCompleteTextView) findViewById(R.id.ProdutoId)).setText("");
       ((AutoCompleteTextView) findViewById(R.id.marcadoprodutoId)).setText("");
       ((AutoCompleteTextView) findViewById(R.id.estabelecimentoId)).setText("");
       ((AutoCompleteTextView) findViewById(R.id.LocalId)).setText("");
       ((EditText) findViewById(R.id.valordoprodutoId)).setText("");
    }
}



