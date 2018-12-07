package com.example.esfig.projetodebloco.viewholder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.esfig.projetodebloco.R;
import com.google.firebase.database.Exclude;
import com.xwray.groupie.ViewHolder;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;



public class PromocaoListViewHouder extends ViewHolder {

    //elementos da viel que vai dentro do recicle view
    public TextView produto;
    public TextView marca;
    public TextView local;
    public TextView preco;
    public ImageButton vinc;
    public ImageButton exclu;
    public ConstraintLayout lnt;

    public PromocaoListViewHouder(@NonNull View rootView) {
        super(rootView);

        produto = itemView.findViewById(R.id.textRcprodut);
        marca = itemView.findViewById(R.id.textRcmarcas);
        local = itemView.findViewById(R.id.textRcLocal);
        preco = itemView.findViewById(R.id.textRcPreco);
        vinc = itemView.findViewById(R.id.btnVincular);
        exclu = itemView.findViewById(R.id.btnExcluir);
        lnt = itemView.findViewById(R.id.lnint);

    }



}


 //mpublic class PromocaoListViewHouder extends AppCompatActivity {

