package com.example.esfig.projetodebloco.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.esfig.projetodebloco.R;


public class ActivityCad extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ProdutoId);
        // Get the string array
        String[] produto = getResources().getStringArray(R.array.produto_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, produto);
        textView.setAdapter(adapter);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.marcadoprodutoId);
        // Get the string array
        String[] marcas = getResources().getStringArray(R.array.marca_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, marcas);
        textView1.setAdapter(adapter1);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.LocalId);
        // Get the string array
        String[] locais1 = getResources().getStringArray(R.array.Locais_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locais1);
        textView2.setAdapter(adapter2);
    }

}


     /*TODO: 22/11/2018
     //Nothing special, create database reference.
   // DatabaseReference database = FirebaseDatabase.getInstance().getReference(); //
    //Create a new ArrayAdapter with your context and the simple layout for the dropdown menu provided by Android
     final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1); //
      Child the root before all the push() keys are found and add a ValueEventListener()
        database.child("felipe").

                addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot datasnapshot) {
        //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
        for (DataSnapshot suggestionSnapshot : datasnapshot.getChildren()) {
        //Get the suggestion by childing the key of the string you want to get.
        String suggestion = suggestionSnapshot.child("produto").getValue(String.class);
        //Add the retrieved string to the list
        autoComplete.add(suggestion);

        }
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        AutoCompleteTextView ACTV = (AutoCompleteTextView) findViewById(R.id.ProdutoId);
        ACTV.setAdapter(autoComplete);
        }
