package com.myweatherapp.data;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.myweatherapp.dao.CityDao;
import com.myweatherapp.entity.City;

import java.util.concurrent.Executors;


@Database(entities = City.class, version = 1,exportSchema = false)
public abstract class CityRoomDatebase  extends RoomDatabase {
    private static CityRoomDatebase INSTANCE;

    public abstract CityDao cityDao();

    public synchronized static CityRoomDatebase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static CityRoomDatebase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                CityRoomDatebase.class,
                "city_datebase")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).cityDao().insertAll(City.populateCity());
                            }
                        });
                    }
                })
                .build();
    }
}
