package com.example.va;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ListRepository {
    private ListDao listDao;
    private LiveData<List<Lift>> lists;

    public ListRepository(Application application){
        ListDatabase listDatabase = ListDatabase.getInstance(application);
        listDao = listDatabase.listDao();
        lists = listDao.getAllLists();
    }

    public void insert(Lift lift){
        new InsertLiftAsyncTask(listDao).execute(lift);
    }

    public void update(Lift lift){
        new UpdateLiftAsyncTask(listDao).execute(lift);
    }

    public void delete(Lift lift){
        new DeleteLiftAsyncTask(listDao).execute(lift);
    }

    public void deleteAllLifts(){
        new DeleteAllLiftsAsyncTask(listDao).execute();
    }

    public LiveData<List<Lift>> getAllLifts() {
        return lists;
    }

    private static class InsertLiftAsyncTask extends AsyncTask<Lift, Void, Void> {

        private ListDao listDao;

        private InsertLiftAsyncTask(ListDao listDao){
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(Lift... lifts) {
            listDao.insert(lifts[0]);
            return null;
        }
    }

    private static class UpdateLiftAsyncTask extends AsyncTask<Lift, Void, Void>{

        private ListDao listDao;

        private UpdateLiftAsyncTask(ListDao listDao){
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(Lift... lifts) {
            listDao.update(lifts[0]);
            return null;
        }
    }

    private static class DeleteLiftAsyncTask extends AsyncTask<Lift, Void, Void>{

        private ListDao listDao;

        private DeleteLiftAsyncTask(ListDao listDao){
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(Lift... lifts) {
            listDao.delete(lifts[0]);
            return null;
        }
    }

    private static class DeleteAllLiftsAsyncTask extends AsyncTask<Void, Void, Void>{

        private ListDao listDao;

        private DeleteAllLiftsAsyncTask(ListDao listDao){
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listDao.deleteAll();
            return null;
        }
    }
}
