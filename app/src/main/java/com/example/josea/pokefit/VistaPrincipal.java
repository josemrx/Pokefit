package com.example.josea.pokefit;


import com.example.josea.pokefit.modelos.Result;

import java.util.List;

public interface VistaPrincipal {
    void notifyDataSetChanged(List<Result> pokemon);
    void navigateToDetails(String nombre);
}