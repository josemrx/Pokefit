package com.example.josea.pokefit;

import android.util.Log;

import com.example.josea.pokefit.api.RestClient;
import com.example.josea.pokefit.api.RetrofitUtils;
import com.example.josea.pokefit.modelos.AbilityFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VistaAbilitiesProvider {
    private VistaAbilities view;

    public VistaAbilitiesProvider(VistaAbilities vista) {
        this.view = vista;
    }

    public void getData() {
        RestClient restClient = RetrofitUtils.getInstance().create(RestClient.class);
        Call<AbilityFeed> call = restClient.getDataAbility();

        call.enqueue(new Callback<AbilityFeed>() {
            @Override
            public void onResponse(Call<AbilityFeed> call, Response<AbilityFeed> response) {
                switch (response.code()) {
                    case 200:
                        AbilityFeed data = response.body();
                        view.notifyDataSetChanged(data.getResults());
                        break;
                    case 401:
                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<AbilityFeed> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
