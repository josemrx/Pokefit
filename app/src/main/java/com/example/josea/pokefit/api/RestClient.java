package com.example.josea.pokefit.api;


import com.example.josea.pokefit.modelos.AbilityFeed;
import com.example.josea.pokefit.modelos.PokemonFeed;
import com.example.josea.pokefit.modelos.TypeFeed;
import com.example.josea.pokefit.modelos.modelosPokemon.ModeloFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestClient {
    @GET("pokemon")
    Call<PokemonFeed> getData();

    @GET("pokemon/{nombre}")
    Call<ModeloFeed> getDataPokemon(@Path("nombre") String nombre);

    @GET("type")
    Call<TypeFeed> getDataTypes();

    @GET("ability")
    Call<AbilityFeed> getDataAbility();




}
