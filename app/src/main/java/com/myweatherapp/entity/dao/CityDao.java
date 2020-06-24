package com.myweatherapp.entity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.myweatherapp.entity.CityEntity;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM CityEntity")
    List<CityEntity> getAll();

    @Query("SELECT * FROM city WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
