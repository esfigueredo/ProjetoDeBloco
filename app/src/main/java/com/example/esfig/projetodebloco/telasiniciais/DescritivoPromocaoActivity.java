package com.example.esfig.projetodebloco.telasiniciais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.esfig.projetodebloco.BO.LocalBo;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Promocao;

import java.util.List;

public class DescritivoPromocaoActivity extends AppCompatActivity {

    Promocao promo ;
    Class call;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, call);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descritivo_promocao);


        promo = (Promocao) getIntent().getSerializableExtra("promo");
        call = (Class) getIntent().getSerializableExtra("myCallClass");

        TextView recebeNome = (TextView)findViewById(R.id.textView_01_recebe_nomeProduto);
        TextView recebeMarca = (TextView)findViewById(R.id.textView_02_recebe_marca);
        TextView recebeLocal = (TextView)findViewById(R.id.textView_05_recebe_cidade);
        TextView valor = (TextView)findViewById(R.id.textView_03_recebe_valor);
        TextView recebeLocalEndereco = (TextView)findViewById(R.id.textView_04_recebe_endereco);

        recebeNome.setText(promo.getProdutoNome());
        recebeMarca.setText(promo.getNomeMarca());
        valor.setText(String.valueOf(promo.getPreco()));
        LocalBo lbo = new LocalBo();

        try {
            lbo.getLocal(promo.getIdLocal(), new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {
                    if(list.size() > 0 ) {
                        recebeLocal.setText(((Local)list.get(0)).getNome());
                        recebeLocalEndereco.setText(((Local)list.get(0)).getEndereco());
                    }
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }


}
