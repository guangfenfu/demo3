package com.example.myfood.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flavor")
public class FlavorEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;

    public FlavorEntity(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
