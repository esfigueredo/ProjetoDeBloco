package com.example.esfig.projetodebloco.telasiniciais;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.esfig.projetodebloco.R;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class FirebaseUIActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_ui_activity);
        public void createSignInIntent() {
            // [START auth_fui_create_intent]
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                    new AuthUI.IdpConfig.TwitterBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
            // [END auth_fui_create_intent]
        }

    }
}
