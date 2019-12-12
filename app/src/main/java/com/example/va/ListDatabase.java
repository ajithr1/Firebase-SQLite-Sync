package com.example.va;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Lift.class}, version = 1,exportSchema = false)
public abstract class ListDatabase extends RoomDatabase {

    private static ListDatabase listDatabase;
    public abstract ListDao listDao();

    public static synchronized ListDatabase getInstance(Context context){
        if (listDatabase == null){
            listDatabase = Room.databaseBuilder(context.getApplicationContext(), ListDatabase.class,
                    "list_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return listDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Populate(listDatabase).execute();
        }
    };

    private static class Populate extends AsyncTask<Void, Void,Void>{

        private ListDao listDao;
        private Populate(ListDatabase listDatabase){
            listDao = listDatabase.listDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listDao.insert(new Lift("Ajith"));
            return null;
        }
    }
}
