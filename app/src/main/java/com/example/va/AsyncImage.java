package com.example.va;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImage extends AsyncTask<String,Integer,String> {

    Context ctx;

    public AsyncImage(Context context){
        ctx = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        String s = strings[0];
        InputStream in;
        try {
            URL url = new URL(s);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();

            in = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line+" \n");
            }

            bufferedReader.close();
            in.close();

            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        AsyncTaskExample.textView.setText(s);
    }
}
