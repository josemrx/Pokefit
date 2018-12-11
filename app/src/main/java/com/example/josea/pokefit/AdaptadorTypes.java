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

public class AdaptadorTypes extends RecyclerView.Adapter<AdaptadorTypes.TypeViewHolder>{

    private List<Result> list;


    private Context context;
    private VistaTypes vista;

    public AdaptadorTypes(Context context, VistaTypes vista) {
        this.list = new ArrayList<>();
        this.context = context;
        this.vista = vista;
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, final int position) {
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
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_type, parent, false));
    }



    public static class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public TypeViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.txtType);
        }
    }

}


