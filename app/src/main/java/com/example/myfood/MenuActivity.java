package com.example.myfood;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myfood.data.MySQLiteOpenHelper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    List<Dish> lstDish = new ArrayList<>();
    Context mContext = this;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    SQLiteDatabase database;
    Cursor cursor;
    String sql="SELECT id, img_url, name FROM dish ORDER BY id";
    RecyclerViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbarTb);

        // Read data from DB, and Save to List<Dish>
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext(), "food.db");
        database = mySQLiteOpenHelper.getWritableDatabase();
        updateDishList(sql);


        // Set RecyclerView, input Data - Image, Name to RecyclerView
        RecyclerView myrv = (RecyclerView)findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this, lstDish);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);


    }

    private void updateDishList(String sql) {
        lstDish.clear();
        cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String imgUrl = cursor.getString(1);
            String name = cursor.getString(2);
            lstDish.add(new Dish(id, imgUrl,name));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.all:
                sql = "SELECT id, img_url, name FROM dish ORDER BY id";
                break;
            case R.id.china:
                sql = "SELECT id, img_url, name FROM dish WHERE style='China' ORDER BY id";
                break;
            case R.id.vietnam:
                sql = "SELECT id, img_url, name FROM dish WHERE style='Vietnam' ORDER BY id";
                break;
            case R.id.korea:
                sql = "SELECT id, img_url, name FROM dish WHERE style='Korea' ORDER BY id";
                break;
            case R.id.map:
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
        updateDishList(sql);
        myAdapter.cancelTasks();
        myAdapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }




// Download Task, save Data to ArrayList
//    private class DownLoadTask extends AsyncTask<List<Dish>, Void, List<Dish>>{
//
//        @Override
//        protected List<Dish> doInBackground(List<Dish>... lists) {
//
//            List<Dish> list = lists[0];
//
//            for(int i = 0; i < list.size(); i++){
//
//                URL url = list.get(i).getUrl();
//                HttpURLConnection connection = null;
//                try{
//                    connection = (HttpURLConnection)url.openConnection();
//                    connection.connect();
//                    InputStream inputStream = connection.getInputStream();
//                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//                    Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
//
//                    list.get(i).setImg(bmp);
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }finally {
//                    connection.disconnect();
//                }
//            }
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(List<Dish> dishes) {
//            // Set RecyclerView, input Data - Image, Name to RecyclerView
//            RecyclerView myrv = (RecyclerView)findViewById(R.id.recyclerview_id);
//            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(mContext, lstDish);
//            myrv.setLayoutManager(new GridLayoutManager(mContext, 3));
//            myrv.setAdapter(myAdapter);
//
//        }
//    }



}
