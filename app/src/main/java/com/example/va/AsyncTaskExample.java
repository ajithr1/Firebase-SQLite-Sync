package com.example.va;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
}
