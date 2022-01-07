package com.yousef.ta3leem.Data.Room.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yousef.ta3leem.Data.Room.DAO.AdminDAO;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;

@Database(entities = {Admin.class} , version = 2)
public abstract class SchoolDataBase extends RoomDatabase {
    private static SchoolDataBase instance;
    //We define it as abstract so that room can generate the necessary code
    public abstract AdminDAO adminDAO();

    //synchronized means only one thread can access this method
    public static synchronized SchoolDataBase getInstance(Context context){
        //check if the database doesn't exist
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SchoolDataBase.class ,"School_Database")
                    .addCallback(roomCallback).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsyncTask(instance).execute();
        }
    };

    //inserting admin when creating the database in the background
    private static class populateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        AdminDAO adminDAO;

        public populateDBAsyncTask(SchoolDataBase SchoolDataBase) {
            adminDAO = SchoolDataBase.adminDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            adminDAO.Insert(new Admin("112233" , "yousef" , "12345678"));
            adminDAO.Insert(new Admin("112234" , "Hashem" , "12345678"));
            return null;
        }
    }

}
