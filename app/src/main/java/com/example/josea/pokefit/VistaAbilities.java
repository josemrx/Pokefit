package com.example.josea.pokefit;


import com.example.josea.pokefit.modelos.Result;

import java.util.List;

public interface VistaAbilities {
    void notifyDataSetChanged(List<Result> ability);
    void navigateToDetails(int id);
}