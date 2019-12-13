package com.example.va;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskExample extends AppCompatActivity {

    AsyncDownload asyncDownload;
    AsyncImage asyncImage;
    static TextView textView;
    static ImageView imageView;

    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        textView = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    public void download(View view) {
        asyncDownload = new AsyncDownload(this);
        asyncDownload.execute("https://newevolutiondesigns.com/images/freebies/cool-wallpaper-1.jpg");
    }

    public void downloadImage(View view) {

        asyncImage = new AsyncImage(this);
        asyncImage.execute("https://www.google.co.in");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public static class AsyncDownload extends AsyncTask<String,Integer, Bitmap> {

        ProgressDialog progressDialog;
        Context ctx;

        public AsyncDownload(Context context){
            ctx = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(ctx);
            progressDialog.setTitle("Downloading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String s = strings[0];
            InputStream in;
            try {
                URL url = new URL(s);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.connect();

                in = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;
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
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            AsyncTaskExample.imageView.setImageBitmap(s);
            Toast.makeText(ctx, "Downloaded", Toast.LENGTH_SHORT).show();
        }
    }

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
}
