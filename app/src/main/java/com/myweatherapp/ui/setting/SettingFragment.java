package com.myweatherapp.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myweatherapp.R;
import com.myweatherapp.adapter.CitiesAdapter;
import com.myweatherapp.entity.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment {

    private SettingViewModel citiesViewModel;
    private CitiesAdapter citiesAdapter;
    private List<City>cities;

    @BindView(R.id.recycleview_cities)
    public RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        citiesViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_setings, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        citiesAdapter = new CitiesAdapter(getActivity(),cities);
        recyclerView.setAdapter(citiesAdapter);

        citiesViewModel.getAllCities().observe(getViewLifecycleOwner(), new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
            citiesAdapter.setCities(cities);
            }
        });



        return view;
    }
}
