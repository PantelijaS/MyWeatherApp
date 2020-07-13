package com.myweatherapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myweatherapp.R;
import com.myweatherapp.adapter.CitiesAdapter;
import com.myweatherapp.entity.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends Fragment  implements CitiesAdapter.OnCitiesListener  {

    private SettingViewModel citiesViewModel;
    private CitiesAdapter citiesAdapter;
    private List<City>cities;

    @BindView(R.id.recycleview_cities)
    public RecyclerView citiesRecyclerView;

    @BindView(R.id.imageButton_add)
    ImageButton addCities;

    @BindView(R.id.text_add_name_cities)
    EditText addCityName;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        citiesViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_setings, container, false);
        ButterKnife.bind(this, view);

        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        citiesRecyclerView.setHasFixedSize(true);
        citiesAdapter = new CitiesAdapter(this);
        citiesRecyclerView.setAdapter(citiesAdapter);

        citiesViewModel.getAllCities().observe(getViewLifecycleOwner(), new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
            citiesAdapter.setCities(cities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                citiesViewModel.delete(citiesAdapter.getCityAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(citiesRecyclerView);

        addCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = addCityName.getText().toString().trim();
                City city = new City(cityName);
                citiesViewModel.insert(city);

            }
        });

        return view;
    }

    @Override
    public void OnCityClick(City city) {

    }
}
