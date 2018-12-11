package com.example.josea.pokefit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.josea.pokefit.modelos.Result;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPrincipal extends RecyclerView.Adapter<AdaptadorPrincipal.PokemonViewHolder> implements Filterable {



    private List<Result> list;
    private ArrayList<Result> filtrada;
    private Filtro filtro;
    private Context context;
    private VistaPrincipal vista;

    public AdaptadorPrincipal(Context context, VistaPrincipal vista) {
        this.list = new ArrayList<>();
        this.filtrada=new ArrayList<>();
        this.context = context;
        this.vista = vista;
        this.filtro = new Filtro();
    }


    @Override
    public void onBindViewHolder(PokemonViewHolder holder, final int position) {
        final Result pokemon = list.get(position);
        holder.tvName.setText(pokemon.getName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vista != null) {
                    vista.navigateToDetails(pokemon.getName());
                }
            }
        });
    }



    public void swap(List<Result> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pokemon_item, parent, false));
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }


    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public PokemonViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);

        }
    }

    public class Filtro extends Filter {
        private AdaptadorPrincipal adapter;
        private Filtro() {
            super();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filtrada.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filtrada.addAll(list);
                System.out.println(list.toString());
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Result resu : list) {
                    if (resu.getName().toLowerCase().contains(filterPattern)) {
                        filtrada.add(resu);
                    }
                }
            }
            results.values = filtrada;
            results.count = filtrada.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           swap(filtrada);
        }
    }

}


