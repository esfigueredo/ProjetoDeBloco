package com.example.esfig.projetodebloco.telasiniciais;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.esfig.projetodebloco.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_minha_lista) {
            Intent intent = new Intent(MainActivity.this, MenuListaActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_promocao) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_desconectar) {
            FirebaseAuth.getInstance().signOut();


            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
            finishAffinity();


        } else if (id == R.id.nav_sair) {
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
            finishAffinity();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
