package com.example.stockapisearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;



public class MainActivity extends AppCompatActivity {

    TextView tvStatus;
    EditText etStock;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        etStock = findViewById(R.id.etStock);
        btnSearch = findViewById(R.id.btnSearch);



/*        try {
          new APIWorker().execute(new URL("https://api.iextrading.com/1.0/stock/goog/quote" + ""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        new APIWorker();


    }

    class APIWorker extends AsyncTask<URL, Void, String >{

        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder result = new StringBuilder();

            try {
                URL googleStock = new URL("https://api.iextrading.com/1.0/stock/goog/quote"); //creating URL object
                HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) googleStock.openConnection(); //obtain a URL connection
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)urls[0].openConnection(); //obtains a https connection and opens it
                InputStream inputStream = new BufferedInputStream(httpsUrlConnection.getInputStream()); //creates a stream to read from https connection

                BufferedReader buffedReader = new BufferedReader(new InputStreamReader(inputStream));//allows the user to read from the stream.

                String line;

                while ((line = buffedReader.readLine()) != null) { //reads until it reaches the end of the file
                    result.append(line); //nicely adds whatever i read to the builder
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            IexTradingStocks iexTradingStocks = gson.fromJson(s, IexTradingStocks.class);

            //List<IexTradingStocks> apiResults = Collections.singletonList(iexTradingStocks);

            tvStatus.setText(iexTradingStocks.getSymbol());




        }
    }
}
