package com.myweatherapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myweatherapp.R;
import com.myweatherapp.entity.City;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.RecyclerViewHolder> {
    Context context;
    private List<City> cities ;

    public CitiesAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cities = cityList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_item, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
    holder.text_city_name.setText(cities.get(position).cityName);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCities (List<City>cities){
        this.cities=cities;
        notifyDataSetChanged();

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_city_name)
        TextView text_city_name;
        @BindView(R.id.imageButton_delete)
        ImageButton imageButton;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}