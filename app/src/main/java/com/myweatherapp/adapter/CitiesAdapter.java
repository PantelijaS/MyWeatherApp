package com.myweatherapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myweatherapp.MainActivity;
import com.myweatherapp.R;
import com.myweatherapp.entity.City;
import com.myweatherapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.RecyclerViewHolder> {
    Context context;
    private List<City> cities = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        City listCity = cities.get(position);
        holder.text_name_cities.setText(listCity.cityName);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCities (List<City>cities){
        this.cities=cities;
        notifyDataSetChanged();
    }

    public City getCityAt (int position){
        return cities.get(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name_cities)
        TextView text_name_cities;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("cityName",cities.get(getAdapterPosition()).cityName);
                    context.startActivity(intent);
                }
            });
        }
    }
}