package com.example.esfig.projetodebloco.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.esfig.projetodebloco.R;

public class ActivityCad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    View.OnClickListener saveClick = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

        }
    };



}
