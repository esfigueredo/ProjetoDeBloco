package com.example.esfig.projetodebloco.telasiniciais;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.esfig.projetodebloco.R;

public class DescritivoPromocaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descritivo_promocao);

        TextView recebeNome = (TextView)findViewById(R.id.textView_02_recebe_nome);
        TextView recebeMarca = (TextView)findViewById(R.id.textView_04_recebe_marca);
        TextView recebeLocal = (TextView)findViewById(R.id.textView_06_recebe_local);


    }
}
