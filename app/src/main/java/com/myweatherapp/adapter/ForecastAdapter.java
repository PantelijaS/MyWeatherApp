package com.myweatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.myweatherapp.units.Common;
import com.myweatherapp.R;
import com.myweatherapp.model.Forcast;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.RecyclerViewHolder> {
        Context context;
        Forcast getForcastModel;

        public ForecastAdapter(Context context, Forcast forcast) {
            this.context = context;
            this.getForcastModel = forcast;
        }


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.forecast_item, null);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(getForcastModel.list.get(position).weather.get(0).icon)
                                .append(".png").toString()).into(holder.imageViewForcast);
            holder.text_day.setText(new StringBuilder(Common.convertToDate(getForcastModel.list.get(position).dt)));
            holder.text_temperature.setText(new StringBuilder(String.valueOf(getForcastModel.list.get(position).main.temp)).append(" °C"));
            holder.text_description.setText(getForcastModel.list.get(position).weather.get(0).description);
            holder.text_rain.setText(new StringBuilder(String.valueOf(getForcastModel.list.get(position).main.temp)).append(" %"));
        }

        @Override
        public int getItemCount() {
            return getForcastModel.list.size();
        }

        public class RecyclerViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.text_day)
            TextView text_day;
            @BindView(R.id.text_temperature)
            TextView text_temperature;
            @BindView(R.id.imageViewForcast)
            ImageView imageViewForcast;
            @BindView(R.id.text_description)
            TextView text_description;
            @BindView(R.id.text_humidity)
            TextView text_rain;

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }