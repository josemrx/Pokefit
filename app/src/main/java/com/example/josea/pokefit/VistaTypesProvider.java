package com.example.josea.pokefit;

import android.util.Log;

import com.example.josea.pokefit.api.RestClient;
import com.example.josea.pokefit.api.RetrofitUtils;
import com.example.josea.pokefit.modelos.TypeFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VistaTypesProvider {
    private VistaTypes view;

    public VistaTypesProvider(VistaTypes vista) {
        this.view = vista;
    }

    public void getData() {
        RestClient restClient = RetrofitUtils.getInstance().create(RestClient.class);
        Call<TypeFeed> call = restClient.getDataTypes();

        call.enqueue(new Callback<TypeFeed>() {
            @Override
            public void onResponse(Call<TypeFeed> call, Response<TypeFeed> response) {
                switch (response.code()) {
                    case 200:
                        TypeFeed data = response.body();
                        view.notifyDataSetChanged(data.getResults());
                        break;
                    case 401:
                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<TypeFeed> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
