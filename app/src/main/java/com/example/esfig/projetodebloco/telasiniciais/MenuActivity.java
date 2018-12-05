package com.example.esfig.projetodebloco.telasiniciais;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.esfig.projetodebloco.BO.ListaBO;
import com.example.esfig.projetodebloco.BO.PromocaoBO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.Util.MyclickListener;
import com.example.esfig.projetodebloco.itemviewholder.PromocaoListItem;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xwray.groupie.GroupAdapter;

import java.util.List;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
        }
    }

    public GroupAdapter adapter =  new GroupAdapter();
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        PromocaoBO pbo = new PromocaoBO();
        try {
            pbo.setEventiListenerPromo(new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {
                    List<Promocao> lpromo = (List<Promocao>) list;
                    RecyclerView PessoaView = findViewById(R.id.promoView);
                    PessoaView.setLayoutManager(new GridLayoutManager(MenuActivity.this, 1));
                    PessoaView.setAdapter(adapter);
                    populateViewListPessoa(lpromo,adapter,listener);
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public MyclickListener listener = new MyclickListener() {
        @Override
        public void onClick(String position) {
            PromocaoBO pbo = new PromocaoBO();
            try {
                pbo.getPromo(new FireBaseCalback() {
                    @Override
                    public <T> void onCalback(List<T> list) {
                        Promocao p = (Promocao) list.get(0);
                        ListaBO lbo = new ListaBO();

                        //lbo.addLista(p);
                    }
                },position);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        }
    };

    public void populateViewListPessoa(List<Promocao> lp, GroupAdapter ga, MyclickListener listener){
        for (Promocao p: lp) {
            ga.add(new PromocaoListItem(listener,p));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_minha_lista) {

        } else if (id == R.id.nav_promocao) {
          //  Intent Intent_promocao = new Intent(PromocaoActivity.this, PromocaoActivity.class);
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
