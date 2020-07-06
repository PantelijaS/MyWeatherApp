package com.myweatherapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_table")
public class City {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String cityName;


    public City(int id,String cityName) {
        this.id=id;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public static City[] populateCity() {
        return new City[] {
                new City(1,"London"),
                new City(2,"New York"),
                new City(3,"Belgrade"),
                new City(4,"Barselona")
        };
    }
}
