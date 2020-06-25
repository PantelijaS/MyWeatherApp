package com.myweatherapp.entity;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.myweatherapp.entity.dao.CityDao;


@Database(entities = CityEntity.class, version = 1)
public abstract class CityRoomDatebase  extends RoomDatabase {

    private static volatile CityRoomDatebase INSTANCE;
    public abstract CityDao cityDao();

    static CityRoomDatebase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CityRoomDatebase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityRoomDatebase.class, "city_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
