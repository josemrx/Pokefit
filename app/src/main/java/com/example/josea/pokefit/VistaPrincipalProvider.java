package com.example.josea.pokefit;

import android.util.Log;

import com.example.josea.pokefit.api.RestClient;
import com.example.josea.pokefit.api.RetrofitUtils;
import com.example.josea.pokefit.modelos.PokemonFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VistaPrincipalProvider {
    private VistaPrincipal view;

    public VistaPrincipalProvider(VistaPrincipal vistaPrincipal) {
        this.view = vistaPrincipal;
    }

    public void getData() {
        RestClient restClient = RetrofitUtils.getInstance().create(RestClient.class);
        Call<PokemonFeed> call = restClient.getData();

        call.enqueue(new Callback<PokemonFeed>() {
            @Override
            public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
                switch (response.code()) {
                    case 200:
                        PokemonFeed data = response.body();
                        view.notifyDataSetChanged(data.getResults());
                        break;
                    case 401:
                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<PokemonFeed> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
