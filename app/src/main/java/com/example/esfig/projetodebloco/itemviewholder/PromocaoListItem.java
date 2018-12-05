package com.example.esfig.projetodebloco.itemviewholder;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.MyclickListener;
import com.example.esfig.projetodebloco.model.Promocao;
import com.example.esfig.projetodebloco.viewholder.PromocaoListViewHouder;
import com.xwray.groupie.Item;

public class PromocaoListItem extends Item<PromocaoListViewHouder> {

    private MyclickListener listener;
    Promocao promocao;

    public PromocaoListItem(MyclickListener listener, Promocao p) {
        this.listener = listener;
        this.promocao = p;
    }

    @Override
    public void bind(@NonNull PromocaoListViewHouder viewHolder, final int position) {
        viewHolder.produto.setText(promocao.getProdutoNome());
        viewHolder.marca.setText(promocao.getNomeMarca());
        viewHolder.local.setText(promocao.getNomeLocal());
        viewHolder.preco.setText(String.valueOf(promocao.getPreco()));
        viewHolder.lnt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                listener.onClick(promocao.getId());
              }
          }
        );
    }

    @Override
    public int getLayout() {
        return R.layout.promo_list_item_viewhouder;
    }

    @NonNull
    @Override
    public PromocaoListViewHouder createViewHolder(@NonNull View itemView) {
        return new PromocaoListViewHouder(itemView);
    }
}
