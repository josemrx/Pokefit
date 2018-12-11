package com.example.josea.pokefit.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {
    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static String parseMessage(Response response) {
        try {
            String json = response.errorBody().string();
            return new JsonParser().parse(json).getAsJsonObject().get("message").getAsString();
        } catch (IOException e) {
            return "Ocurrió un error en la respuesta";
        }
    }
}
