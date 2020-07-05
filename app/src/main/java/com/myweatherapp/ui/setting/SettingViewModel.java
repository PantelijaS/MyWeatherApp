package com.myweatherapp.ui.setting;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myweatherapp.entity.City;
import com.myweatherapp.repository.CityRepository;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {
    private CityRepository cityRepository;
    private LiveData<List<City>>allCities;

    public SettingViewModel(@NonNull Application application) {
        super(application);
        cityRepository = new CityRepository(application);
        allCities = cityRepository.getAllCity();
    }

    public void insert(City city){
        cityRepository.insert(city);
    }

    public void delete(City city){
        cityRepository.delete(city);
    }



    public LiveData<List<City>>getAllCities(){
        return allCities;
    }

}