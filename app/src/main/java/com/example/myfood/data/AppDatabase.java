package com.example.myfood.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FlavorEntity.class, RestaurantEntity.class, DishEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase sInstance;

    public static AppDatabase getDatabase(Context context) {
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDatabase.class, "food.db").build();
        }
        return sInstance;
    }

    public static void onDestroy(){
        sInstance = null;
    }

    public abstract FlavorDao getFlavorDao();
    public abstract RestaurantDao getRestaurantDao();
    public abstract DishDao getDishDao();
}
