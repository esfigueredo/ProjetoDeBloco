package com.example.esfig.projetodebloco.telasiniciais;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.esfig.projetodebloco.BO.ListaBO;
import com.example.esfig.projetodebloco.BO.PromocaoBO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.Config;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.Util.MyclickListener;
import com.example.esfig.projetodebloco.activities.ActivityCad;
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
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        nomeMenuView.setText(currentUser.getDisplayName());
        emailMenuView.setText(currentUser.getEmail());

        if (currentUser == null) {
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
        }
    }

    private FirebaseUser currentUser;
    public GroupAdapter adapter =  new GroupAdapter();
    private TextView emailMenuView;
    private TextView nomeMenuView;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nomeMenuView = navigationView.getHeaderView(0).findViewById(R.id.NameUser);
        emailMenuView = navigationView.getHeaderView(0).findViewById(R.id.EmailUser);

        PromocaoBO pbo = new PromocaoBO();
        try {
            pbo.setEventiListenerPromo(new FireBaseCalback() {
                @Override
                public <T> void onCalback(List<T> list) {
                    List<Promocao> lpromo = (List<Promocao>) list;
                    RecyclerView PessoaView = findViewById(R.id.promoView);
                    PessoaView.setLayoutManager(new GridLayoutManager(MenuActivity.this, 1));
                    PessoaView.setAdapter(adapter);
                    populateViewListPessoa(lpromo,adapter,listener,presslistener);
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
            snackbar.show();
        } catch (InstantiationException e) {
            e.printStackTrace();
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    public MyclickListener presslistener = new MyclickListener() {
        @Override
        public void onClick(String position) {
            PromocaoBO pbo = new PromocaoBO();

            try {
                pbo.getPromo(new FireBaseCalback() {
                    @Override
                    public <T> void onCalback(List<T> list) {
                        Promocao p = (Promocao) list.get(0);
                        Intent intent = new Intent(MenuActivity.this, DescritivoPromocaoActivity.class);
                        intent.putExtra("myCallClass", MenuActivity.class);
                        intent.putExtra("promo", p);
                        startActivity(intent);
                    }
                },position);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            } catch (InstantiationException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            }


        }
    };

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
                        lbo.addLista(p,Config.ContantList);
                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.content_menu_layoutdiv), "Promoção Associada a lista.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                },position);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            } catch (InstantiationException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content_menu_layoutdiv), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            }


        }
    };

    public void populateViewListPessoa(List<Promocao> lp, GroupAdapter ga, MyclickListener listener, MyclickListener presslistener){
        for (Promocao p: lp) {
            ga.add(new PromocaoListItem(listener,presslistener,p,true));
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

        getMenuInflater().inflate(R.menu.menu_top_titlepromocao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_promo:
                Intent intent = new Intent(this, ActivityCad.class);
                startActivity(intent);
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
            Intent intent = new Intent(MenuActivity.this, MenuListaActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Tela Lista", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_promocao) {
            Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Tela promoção", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_desconectar) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Você se desconectou.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
            finishAffinity();


        } else if (id == R.id.nav_sair) {
            Intent intent = new Intent(this, TelaLogin.class);
            startActivity(intent);
            finishAffinity();
            Toast.makeText(this, "Você saiu do App.", Toast.LENGTH_LONG).show();

    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
