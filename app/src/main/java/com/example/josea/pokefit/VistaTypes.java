package com.example.josea.pokefit;


import com.example.josea.pokefit.modelos.Result;

import java.util.List;

public interface VistaTypes {
    void notifyDataSetChanged(List<Result> types);
    void navigateToDetails(int id);
}