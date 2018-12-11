package com.example.josea.pokefit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.josea.pokefit.modelos.Result;

import java.util.List;

public class ActividadPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, VistaPrincipal{

    private RecyclerView rv_pokemon;
    AdaptadorPrincipal adapter;
    VistaPrincipalProvider provider;
    EditText busqueda;
    Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__principal);

        btnAtras = findViewById(R.id.btnVolver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Context context = this;
        rv_pokemon = (RecyclerView)findViewById(R.id.rv_pokemon);
        adapter = new AdaptadorPrincipal(ActividadPrincipal.this, this);
        rv_pokemon.setAdapter(adapter);
        rv_pokemon.setLayoutManager(new LinearLayoutManager(this));
        provider = new VistaPrincipalProvider(this);
        provider.getData();

        //para la busqueda por nombre
        busqueda = findViewById(R.id.edtBusqueda);
        busqueda.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(arg0);
                btnAtras.setVisibility(View.VISIBLE);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void vuelveAtras(View v){
        startActivity(new Intent(this, ActividadPrincipal.class));
        finish();
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
        getMenuInflater().inflate(R.menu.menu__principal, menu);
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

        if (id == R.id.listaPrincipal) {
            startActivity(new Intent(this, ActividadPrincipal.class));
            finish();

        } else if (id == R.id.listaTipos) {
            //lista tipos
            startActivity(new Intent(this, ActividadTypes.class));
            finish();

        }else if (id == R.id.listaAbilidades) {
            //lista Abilidades
            startActivity(new Intent(this, ActividadAbilities.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void notifyDataSetChanged(List<Result> pokemon) {
        adapter.swap(pokemon);
    }

    @Override
    public void navigateToDetails(String nombre) {
        startActivity(new Intent(this, ActividadVerPokemon.class).putExtra("idPoke", nombre));
    }
}
