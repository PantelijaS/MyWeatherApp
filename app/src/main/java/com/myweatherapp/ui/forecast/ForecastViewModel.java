package com.myweatherapp.ui.forecast;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myweatherapp.model.Forcast;

import java.util.List;

public class ForecastViewModel extends AndroidViewModel {

    private Forcast forcast;
    private LiveData<List<Forcast>> forcastLiveData;

    public ForecastViewModel(@NonNull Application application) {
        super(application);

    }
}