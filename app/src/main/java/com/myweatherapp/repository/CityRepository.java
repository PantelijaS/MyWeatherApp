package com.myweatherapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.myweatherapp.entity.City;
import com.myweatherapp.data.CityRoomDatebase;
import com.myweatherapp.dao.CityDao;

import java.util.List;

public class CityRepository {
    private CityDao cityDao;
    private LiveData<List<City>> allCity;


    public CityRepository (Application application){
        CityRoomDatebase cityRoomDatebase = CityRoomDatebase.getInstance(application);
        cityDao = cityRoomDatebase.cityDao();
        allCity = cityDao.loadAllCities();
    }

    public void insert (City city){
    new InsertCityAsyncTask(cityDao).execute(city);
    }

    public void delete (City city){
        new DeleteCityAsyncTask(cityDao).execute(city);
    }

    public LiveData<List<City>> getAllCity(){
        return allCity;
    }

    private static class InsertCityAsyncTask extends AsyncTask<City,Void,Void>{

        private CityDao cityDao;
        private InsertCityAsyncTask(CityDao cityDao){
            this.cityDao=cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
        cityDao.insert(cities[0]);
        return null;
        }
    }

    private static class DeleteCityAsyncTask extends AsyncTask<City,Void,Void>{

        private CityDao cityDao;
        private DeleteCityAsyncTask(CityDao cityDao){
            this.cityDao=cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            cityDao.delete(cities[0]);
            return null;
        }
    }
}
