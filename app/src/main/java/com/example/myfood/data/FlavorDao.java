package com.example.myfood.data;

import android.view.View;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface FlavorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFlavor(FlavorEntity flavorEntity);
}
