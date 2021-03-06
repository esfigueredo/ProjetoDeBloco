package com.example.esfig.projetodebloco.telasiniciais;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esfig.projetodebloco.BO.ListaBO;
import com.example.esfig.projetodebloco.BO.ListaPromocoesBO;
import com.example.esfig.projetodebloco.BO.PromocaoBO;
import com.example.esfig.projetodebloco.R;
import com.example.esfig.projetodebloco.Util.Config;
import com.example.esfig.projetodebloco.Util.FireBaseCalback;
import com.example.esfig.projetodebloco.Util.MyclickListener;
import com.example.esfig.projetodebloco.itemviewholder.PromocaoListItem;
import com.example.esfig.projetodebloco.model.Lista;
import com.example.esfig.projetodebloco.model.Promocao;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xwray.groupie.GroupAdapter;

import java.util.List;

public class MenuListaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    public GroupAdapter adapter =  new GroupAdapter();
    private TextView emailMenuView;
    private TextView nomeMenuView;
    private InterstitialAd mInterstitialAd;

    private AdView mAdView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lista);
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

        try {
        ListaBO lbo =  new ListaBO();
        lbo.getLista(new FireBaseCalback() {
            @Override
            public <T> void onCalback(List<T> list) {
                List<Lista> l = (List<Lista>) list;
                ((TextView) findViewById(R.id.nomeLista)).setText(l.get(0).getNome());
            }
        },Config.ContantList);

        Load();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
            snackbar.show();
        } catch (InstantiationException e) {
            e.printStackTrace();
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
            snackbar.show();
        }


        MobileAds.initialize(this, "ca-app-pub-9404483305208909~3149168131");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);
        /*
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstitialAd.loadAd(request);
        mInterstitialAd.show();


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        mInterstitialAd.show();
           */
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
                        Intent intent = new Intent(MenuListaActivity.this, DescritivoPromocaoActivity.class);
                        intent.putExtra("myCallClass", MenuListaActivity.class);
                        intent.putExtra("promo", p);
                        startActivity(intent);
                    }
                },position);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            } catch (InstantiationException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    };

    public MyclickListener listener = new MyclickListener() {
        @Override
        public void onClick(String position) {
            ListaPromocoesBO lbo =  new ListaPromocoesBO();
            lbo.remove(Config.ContantList,position);

            try {

                Load();
            } catch (InstantiationException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ContentViewListMenu), "Ocorreu um erro, contate ao suporte", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        }


    };

    private void Load() throws InstantiationException, IllegalAccessException {
        ListaPromocoesBO Lpbo = new ListaPromocoesBO();
        Lpbo.setEventiListenerPromo(new FireBaseCalback() {
            @Override
            public <T> void onCalback(List<T> list) {
                List<Promocao> lpromo = (List<Promocao>) list;
                adapter.clear();
                RecyclerView ListPromoView = findViewById(R.id.ListView);
                ListPromoView.setLayoutManager(new GridLayoutManager(MenuListaActivity.this, 1));
                ListPromoView.setAdapter(adapter);
                populateViewListPessoa(lpromo,adapter,listener,presslistener);
            }
        },Config.ContantList);
    }

    public void populateViewListPessoa(List<Promocao> lp, GroupAdapter ga, MyclickListener listener, MyclickListener presslistener){
        for (Promocao p: lp) {
            ga.add(new PromocaoListItem(listener,presslistener,p,false));
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
        getMenuInflater().inflate(R.menu.menu_lista, menu);
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
            Intent intent = new Intent(MenuListaActivity.this, MenuListaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_promocao) {
            Intent intent = new Intent(MenuListaActivity.this, MenuActivity.class);
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
