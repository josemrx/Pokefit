package com.example.josea.pokefit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josea.pokefit.modelos.Result;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorAbilities extends RecyclerView.Adapter<AdaptadorAbilities.AbilityViewHolder>{

    private List<Result> list;


    private Context context;
    private VistaAbilities vista;

    public AdaptadorAbilities(Context context, VistaAbilities vista) {
        this.list = new ArrayList<>();
        this.context = context;
        this.vista = vista;
    }

    @Override
    public void onBindViewHolder(AbilityViewHolder holder, final int position) {
        final Result types = list.get(position);
        holder.tvName.setText(types.getName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vista != null) {
                    vista.navigateToDetails(position+1);
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
    public AbilityViewHolder onCreateViewHolder(ViewGroup parent, int viewAbility) {
        return new AbilityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ability, parent, false));
    }



    public static class AbilityViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public AbilityViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.txtAbility);
        }
    }

}


