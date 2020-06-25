package com.myweatherapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city")
public class CityEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String cityName;

}
