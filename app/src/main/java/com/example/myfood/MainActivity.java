package com.example.myfood;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myfood.data.AppDatabase;
import com.example.myfood.data.FlavorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
ProgressBar progressBar;
Handler handler;
Runnable runnable;
Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //Hide ActionBar
        //Go to app/manifests/AndroidManifest.xml    in <application>tag, change the android:theme
        // android:theme="@style/Theme.AppCompat.NoActionBar">


        //ProgressBar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                timer.cancel();
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }

        }, 5000, 500);



// Not use       new InitDatabase().execute();
    }


    /// Not use
    private class InitDatabase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            List<FlavorEntity> flavorList = new ArrayList<>();

            flavorList.add(new FlavorEntity("Spicy"));
            flavorList.add(new FlavorEntity("Sweet"));
            flavorList.add(new FlavorEntity("Normal"));
            flavorList.add(new FlavorEntity("Multiple"));

            for(FlavorEntity entity : flavorList){
                AppDatabase.getDatabase(getApplicationContext());
            }
            return null;
        }
    }



}
