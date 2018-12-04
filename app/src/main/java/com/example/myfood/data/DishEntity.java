package com.example.myfood.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish", foreignKeys = {@ForeignKey(entity = RestaurantEntity.class, parentColumns = "id", childColumns = "restaurant_id"),
                                           @ForeignKey(entity = FlavorEntity.class, parentColumns = "id", childColumns = "flavor_id")})
public class DishEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "img_url")
    private String imgUrl;

    private String name;
    private double price;

    @ColumnInfo(name = "flavor_id")
    private int flavorId;

    private String ingredient;


    @ColumnInfo(name = "restaurant_id")
    private int restaurantId;

    @ColumnInfo(name = "available_time")
    private String availableTime;

    @ColumnInfo(name = "cooking_instruction")
    private String cookingInstruction;

    public DishEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlavorId() {
        return flavorId;
    }

    public void setFlavorId(int flavorId) {
        this.flavorId = flavorId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public String getCookingInstruction() {
        return cookingInstruction;
    }

    public void setCookingInstruction(String cookingInstruction) {
        this.cookingInstruction = cookingInstruction;
    }
}
