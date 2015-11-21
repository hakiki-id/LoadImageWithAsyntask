package com.hakiki95.imageloadjson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnHit, btnClear;

    private EditText url;
    private ImageView img1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHit = (Button) findViewById(R.id.btnHit);
        btnClear = (Button) findViewById(R.id.btnClear);

        url = (EditText) findViewById(R.id.url);
        img1 = (ImageView) findViewById(R.id.imgView1);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.setText(null);
                img1.setImageBitmap(null);
                Toast.makeText(MainActivity.this, "Url dihapus", Toast.LENGTH_SHORT).show();
            }
        });
        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String geturl = url.getText().toString();
                new DownloadImage().execute(geturl);
            }
        });
    }


    private class DownloadImage extends AsyncTask<String,String,Bitmap> {


        @Override
        protected Bitmap doInBackground(String... Urls) {
            String UrlDisplay = Urls[0];
            Bitmap Image = null;

            try {
                InputStream is = new URL(UrlDisplay).openStream();
                Image = BitmapFactory.decodeStream(is);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Image;

        }



        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            img1.setImageBitmap(result);
        }
    }


}