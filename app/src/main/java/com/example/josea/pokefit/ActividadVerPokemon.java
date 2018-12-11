package com.example.josea.pokefit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josea.pokefit.api.RestClient;
import com.example.josea.pokefit.api.RetrofitUtils;
import com.example.josea.pokefit.modelos.modelosPokemon.ModeloFeed;
import com.example.josea.pokefit.modelos.modelosPokemon.Type;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadVerPokemon extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nombre, numero, tipo;
    ImageView front, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ver_pokemon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String nombre = getIntent().getStringExtra("idPoke");
        this.nombre = findViewById(R.id.txtNom);
        numero = findViewById(R.id.txtDex);
        tipo = findViewById(R.id.txtTipo);
        front = findViewById(R.id.imgFront);
        back = findViewById(R.id.imgBack);
        getDataPoke(nombre);
    }

    public void getDataPoke(String nombre) {
        RestClient restClient = RetrofitUtils.getInstance().create(RestClient.class);
        Call<ModeloFeed> call = restClient.getDataPokemon(nombre);

        call.enqueue(new Callback<ModeloFeed>() {
            @Override
            public void onResponse(Call<ModeloFeed> call, Response<ModeloFeed> response) {
                switch (response.code()) {
                    case 200:
                        ModeloFeed data = response.body();
                        rellenaDatos(data);
                        break;
                    case 401:
                        System.out.println("Error 401");
                        break;
                    default:
                        System.out.println("Error desconocido");
                        break;
                }
            }

            @Override
            public void onFailure(Call<ModeloFeed> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });


    }



    private void rellenaDatos(ModeloFeed pokemon){
        nombre.setText(pokemon.getName());
        numero.setText(pokemon.getId()+"");
        Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(front);
        Picasso.get().load(pokemon.getSprites().getBackDefault()).into(back);
        List<Type> listaTipos=pokemon.getTypes();
        String tipos="";
        for (int i = 0; i < listaTipos.size(); i++)
        {
            tipos+=listaTipos.get(i).getType().getName()+" / ";
        }
        tipo.setText(tipos);


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
        getMenuInflater().inflate(R.menu.actividad_ver_pokemon, menu);
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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
