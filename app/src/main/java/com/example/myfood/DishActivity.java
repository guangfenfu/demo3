package com.example.myfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfood.data.MySQLiteOpenHelper;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DishActivity extends AppCompatActivity {
    ImageView dish_imageview;
    TextView dish_name;
    TextView dish_price;
    TextView dish_flavor;
    TextView dish_ingredient;
    TextView dish_restaurant;
    TextView dish_address;
    TextView dish_open_time;
    TextView dish_available_time;
    TextView dish_style;
    TextView dish_cooking_instruction;
    Bitmap bmp;
    Dish dish;
    String img_url;
    String sql;
    SQLiteDatabase database;
    Cursor cursor;
    LikeButton heart_button;
    int is_favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String textColor = sharedPref.getString("text_color_list", null);
        int colorValue = 0;
        if(textColor.equals("yellow")){
            colorValue = 0xffffff80;
        }else if(textColor.equals("white")){
            colorValue = 0xffffffff;
        }else if(textColor.equals("blue")){
            colorValue = 0xff80ffff;
        }

        dish_imageview = (ImageView)findViewById(R.id.dish_imageview);
        dish_name = (TextView)findViewById(R.id.dish_name);
        dish_price = (TextView)findViewById(R.id.dish_price);
        dish_flavor = (TextView)findViewById(R.id.dish_flavor);
        dish_ingredient = (TextView)findViewById(R.id.dish_ingredient);
        dish_restaurant = (TextView)findViewById(R.id.dish_restaurant);
        dish_address = (TextView)findViewById(R.id.dish_address);
        dish_open_time = (TextView)findViewById(R.id.dish_open_time);
        dish_available_time = (TextView)findViewById(R.id.dish_available_time);
        dish_style = (TextView)findViewById(R.id.dish_style);
        dish_cooking_instruction = (TextView)findViewById(R.id.dish_cooking_instruction);
        heart_button = (LikeButton)findViewById(R.id.heart_button);

        dish_name.setTextColor(colorValue);
        dish_cooking_instruction.setTextColor(0xff0000ff);


        Intent intent = this.getIntent();
        final int dish_id =intent.getIntExtra("id", 0);
        Bitmap bm = (Bitmap)intent.getParcelableExtra("img");

        sql = "SELECT img_url, dish.name, price, flavor.description, ingredient, " +
                "restaurant.name, restaurant.address, restaurant.open_time, available_time, " +
                "style, cooking_instruction_url, is_favourite FROM flavor, restaurant, dish " +
                "WHERE dish.id=" + dish_id + " AND dish.flavor_id=flavor.id AND dish.restaurant_id=restaurant.id";

        // Read data from DB, and Save to List<Dish>
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext(), "food.db");
        database = mySQLiteOpenHelper.getWritableDatabase();
        cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            String url = cursor.getString(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String flvor = cursor.getString(3);
            String ingredient = cursor.getString(4);
            String restName = cursor.getString(5);
            String restAddress = cursor.getString(6);
            String restOpenTime = cursor.getString(7);
            String restAvailableTime = cursor.getString(8);
            String style = cursor.getString(9);
            String cookingInstruction = cursor.getString(10);
            is_favourite = cursor.getInt(11);



            dish_imageview.setImageResource(android.R.drawable.ic_menu_camera);
            dish_name.setText("Dish name: "+ name);
            dish_price.setText("Price: $ "+ price+"");
            dish_flavor.setText("Flavor: "+ flvor);
            dish_ingredient.setText("Ingredients: "+ ingredient);
            dish_restaurant.setText("Restaurant: "+ restName);
            dish_address.setText("Address: " + restAddress);
            dish_open_time.setText("Open time: " + restOpenTime);
            dish_available_time.setText("Dish available time: " + restAvailableTime);
            dish_style.setText("Style: " + style);
            dish_cooking_instruction.setText(cookingInstruction);

            try{
                new DownloadTask().execute(new URL(url));
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
        }
        database.close();


        if(is_favourite == 1){
            heart_button.setLiked(true);
        }else {
            heart_button.setLiked(false);
        }


        heart_button.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                updateDb(1);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                updateDb(0);
            }

            private void updateDb(int value){
                String sql = "Update dish SET is_favourite = " + value + " WHERE id = " + dish_id;
                MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext(), "food.db");
                database = mySQLiteOpenHelper.getWritableDatabase();
                database.execSQL(sql);

                database.close();
            }
        });



    }

    private class DownloadTask extends AsyncTask<URL, Void, Bitmap>{
        Bitmap bmp;

        @Override
        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try{
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bmp = BitmapFactory.decodeStream(bufferedInputStream);
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            dish_imageview.setImageBitmap(bitmap);
        }
    }

}
