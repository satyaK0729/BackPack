package com.example.packyourbag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.packyourbag.Adapter.Adapter;
import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Data.AppData;
import com.example.packyourbag.Database.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Creating the required variables
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;    //adaptor variable
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Connecting activity main with main activity
        getSupportActionBar().hide();   //hides the default action bar
        recyclerView = findViewById(R.id.recyclerView);

        //calling the 2 methods addAddTitles(), addAllImages()
        addAddTitles();
        addAllImages();
        persistAppData();
        database = RoomDB.getInstance(this);
        System.out.println("-------------------->>"+database.mainDao().getAllSelected(false).get(0).getItemname());

        //Creating the adaptor object, and look what we send as a aurgument in activity parameter
        adapter = new Adapter(this, titles,images, MainActivity.this);
        //Here we are created a grid layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        //here we are setting the adaptor in the grid layout
        recyclerView.setAdapter(adapter);
    }


    public static final int TIME_INTERVAL = 2000;

    private long mBackPressed;

    public void onBackPressed() {   //this function is for the pop up message when you press the back button to exit
        if(mBackPressed+TIME_INTERVAL > System.currentTimeMillis()) {   //if you press back button for more than one time, then the app will exit, no pop up is shown
            super.onBackPressed();
            return;
        } else {    //else we have to ask the user
            Toast.makeText(this, "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    private void persistAppData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        database = RoomDB.getInstance(this);
        AppData appData = new AppData(database);
        int last = prefs.getInt(AppData.LAST_VERSION, 0);
        if(!prefs.getBoolean(MyConstants.FIRST_TIME_CAMEL_CASE,false)) {
            appData.persistAllData();
            editor.putBoolean(MyConstants.FIRST_TIME_CAMEL_CASE,true);
            editor.commit();
        } else if (last < AppData.NEW_VERSION) {
            database.mainDao().deleteAllSystemsItems(MyConstants.SYSTEM_SMALL);
            appData.persistAllData();
            editor.putInt(AppData.LAST_VERSION,AppData.NEW_VERSION);
            editor.commit();
        }

    }

    private void addAddTitles() {   //adding all the titles
        titles = new ArrayList<>();
        titles.add(MyConstants.BASIC_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.CLOTHING_CAMEL_CASE);
        titles.add(MyConstants.PERSONAL_CARE_CAMEL_CASE);
        titles.add(MyConstants.BABY_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.HEALTH_CAMEL_CASE);
        titles.add(MyConstants.TECHNOLOGY_CAMEL_CASE);
        titles.add(MyConstants.FOOD_CAMEL_CASE);
        titles.add(MyConstants.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstants.CAR_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstants.NEEDS_CAMEL_CASE);
        titles.add(MyConstants.MY_LIST_CAMEL_CASE);
        titles.add(MyConstants.MY_SELECTIONS_CAMEL_CASE);
    }

    private void addAllImages() {   //adding all the images
        images = new ArrayList<>();
        images.add(R.drawable.s1);
        images.add(R.drawable.s2);
        images.add(R.drawable.s3);
        images.add(R.drawable.s4);
        images.add(R.drawable.s5);
        images.add(R.drawable.s6);
        images.add(R.drawable.s7);
        images.add(R.drawable.s8);
        images.add(R.drawable.s9);
        images.add(R.drawable.s10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);
    }
}