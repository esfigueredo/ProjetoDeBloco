package com.example.esfig.projetodebloco.viewholder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.example.esfig.projetodebloco.R;
import com.xwray.groupie.ViewHolder;

public class PromocaoListViewHouder extends ViewHolder {

    //elementos da viel que vai dentro do recicle view
    public TextView produto;
    public TextView marca;
    public TextView local;
    public TextView preco;
    public ConstraintLayout lnt;

    public PromocaoListViewHouder(@NonNull View rootView) {
        super(rootView);

        produto= itemView.findViewById(R.id.textRcprodut);
        marca  = itemView.findViewById(R.id.textRcmarcas);
        local = itemView.findViewById(R.id.textRcLocal);
        preco = itemView.findViewById(R.id.textRcPreco);
        lnt = itemView.findViewById(R.id.lnint);

    }

}
