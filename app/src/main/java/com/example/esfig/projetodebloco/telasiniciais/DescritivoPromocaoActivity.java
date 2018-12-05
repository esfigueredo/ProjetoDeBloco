package com.example.esfig.projetodebloco.telasiniciais;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.esfig.projetodebloco.BO.LocalBo;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.model.Local;
import com.example.esfig.projetodebloco.model.Promocao;

import java.util.List;

public class DescritivoPromocaoActivity extends AppCompatActivity {

    Promocao promo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descritivo_promocao);

        promo = (Promocao) getIntent().getSerializableExtra("promo");

        TextView recebeNome = (TextView)findViewById(R.id.textView_02_recebe_nome);
        TextView recebeMarca = (TextView)findViewById(R.id.textView_04_recebe_marca);
        TextView recebeLocal = (TextView)findViewById(R.id.textView_06_recebe_local);
        TextView recebeLocalEndereco = (TextView)findViewById(R.id.textView_06_recebe_local);

        recebeNome.setText(promo.getProdutoNome());
        recebeMarca.setText(promo.getNomeMarca());

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
