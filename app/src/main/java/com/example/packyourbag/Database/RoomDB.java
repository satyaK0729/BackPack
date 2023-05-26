package com.example.packyourbag.Database;

import  android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.packyourbag.Dao.ItemsDao;
import com.example.packyourbag.Models.Items;

@Database(entities = Items.class, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static String DATABASE_NAME = "MyDb";

    public synchronized static RoomDB getInstance(Context context) {
        if(database == null) {  //if the data base = null, then we will create a new instance, if not null, then we will return the data base object
            database = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();   //creating a database
        }
        return database;
    }

    public abstract ItemsDao mainDao();
}
