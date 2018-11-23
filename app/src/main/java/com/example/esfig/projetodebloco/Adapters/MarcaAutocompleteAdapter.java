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
import com.example.esfig.projetodebloco.model.Marca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarcaAutocompleteAdapter extends ArrayAdapter<Marca> {

    //https://medium.com/@droidbyme/autocomplete-textview-in-android-a1bf5fc112f6

    private Context context;
    List<Marca> list_marca,tempItems, suggestions;
    int resourceID;

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        resourceID = resource;
        this.context = context;
        suggestions = new ArrayList<>();

    }

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        resourceID = resource;
        this.context = context;
        suggestions = new ArrayList<>();
    }

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource, @NonNull Marca[] objects) {
        super(context, resource, objects);
        list_marca = Arrays.asList(objects);
        resourceID = resource;
        this.context = context;
        suggestions = new ArrayList<>();
        tempItems = new ArrayList<>(list_marca);
    }

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Marca[] objects) {
        super(context, resource, textViewResourceId, objects);
        list_marca = Arrays.asList(objects);
        resourceID = resource;
        this.context = context;
        suggestions = new ArrayList<>();
        tempItems = new ArrayList<>(list_marca);
    }

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource, @NonNull List<Marca> objects) {
        super(context, resource, objects);
        list_marca = objects;
        this.context = context;
        resourceID = resource;
        suggestions = new ArrayList<>();
        tempItems = new ArrayList<>(list_marca);
    }

    public MarcaAutocompleteAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Marca> objects) {
        super(context, resource, textViewResourceId, objects);
        list_marca = objects;
        this.context = context;
        resourceID = resource;
        suggestions = new ArrayList<>();
        tempItems = new ArrayList<>(list_marca);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceID, parent, false);
            }
            Marca marca = getItem(position);
            TextView name = view.findViewById(R.id.textView);
            name.setText(marca.getMarca());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public int getCount() {
        return list_marca.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return MarcaFilter;
    }

    @Nullable
    @Override
    public Marca getItem(int position) {
        return list_marca.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private Filter MarcaFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Marca marca = (Marca) resultValue;
            return marca.getMarca();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Marca marca: tempItems) {
                    if (marca.getMarca().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(marca);
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
            ArrayList<Marca> tempValues = (ArrayList<Marca>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Marca fruitObj : tempValues) {
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
