package com.example.esfig.projetodebloco.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.model.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutoAutocompleatAdapter extends ArrayAdapter<Produto> {

    //https://medium.com/@droidbyme/autocomplete-textview-in-android-a1bf5fc112f6

    List<Produto> list_produto, suggestions;
    int resourceID;

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        resourceID = resource;
    }

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        resourceID = resource;
    }

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource, @NonNull Produto[] objects) {
        super(context, resource, objects);
        list_produto = Arrays.asList(objects);
        resourceID = resource;
    }

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Produto[] objects) {
        super(context, resource, textViewResourceId, objects);
        list_produto = Arrays.asList(objects);
        resourceID = resource;
    }

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource, @NonNull List<Produto> objects) {
        super(context, resource, objects);
        list_produto = objects;
        resourceID = resource;
    }

    public ProdutoAutocompleatAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Produto> objects) {
        super(context, resource, textViewResourceId, objects);
        list_produto = objects;
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                view = inflater.inflate(resourceID, parent, false);
            }
            Produto produto = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.textView);
            name.setText(produto.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public int getCount() {
        return list_produto.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return ProdutoFilter;
    }

    @Nullable
    @Override
    public Produto getItem(int position) {
        return list_produto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private Filter ProdutoFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Produto produto = (Produto) resultValue;
            return produto.getNome();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Produto fruit: list_produto) {
                    if (fruit.getNome().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Produto> tempValues = (ArrayList<Produto>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Produto fruitObj : tempValues) {
                    add(fruitObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }


    };

}
