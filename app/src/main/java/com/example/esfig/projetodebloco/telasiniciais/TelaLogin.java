package com.example.esfig.projetodebloco.telasiniciais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esfig.projetodebloco.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
    }
}
