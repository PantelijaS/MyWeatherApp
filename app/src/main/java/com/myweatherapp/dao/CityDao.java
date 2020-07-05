package com.myweatherapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.myweatherapp.entity.City;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insert(City city);

    @Insert
    void insertAll(City... cities);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM city")
    LiveData<List<City>> loadAllCities();



}
