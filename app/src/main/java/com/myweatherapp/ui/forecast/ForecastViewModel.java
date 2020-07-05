package com.myweatherapp.ui.forecast;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myweatherapp.entity.City;
import com.myweatherapp.model.Forcast;
import com.myweatherapp.repository.CityRepository;

import java.util.List;

public class ForecastViewModel extends AndroidViewModel {

    private Forcast forcast;
    private LiveData<List<Forcast>>forcastLiveData;

    public ForecastViewModel(@NonNull Application application) {
        super(application);

    }
}