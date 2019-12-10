package com.example.va;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncDownload extends AsyncTask<String,Integer, Bitmap> {

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
