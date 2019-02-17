package com.example.stockapisearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
          new A
          PIWorker().execute(new URL("https://api.iextrading.com/1.0/stock/goog/quote" + ""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        btnSearch.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
     switch(v.getId()) {
         case R.id.btnSearch:
             searchClicked();
             break;
        }
    }

    private void searchClicked() {
        String symbol = etStock.getText().toString();
        String url = "https://api.iextrading.com/1.0/stock/"+ symbol +"/quote";

        try{
            new APIWorker().execute(new URL(url));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    class APIWorker extends AsyncTask<URL, Void, String >{

        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder result = new StringBuilder();

            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)urls[0].openConnection(); //connection to server
                InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());//collect info from it,buffered perfect for our scenario

                BufferedReader buffedReader = new BufferedReader(new InputStreamReader(inputStream));
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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            IexTradingStocks iexTradingStocks = gson.fromJson(result, IexTradingStocks.class);

            String symbol = iexTradingStocks.getSymbol();
            String companyName = iexTradingStocks.getCompanyName();
            String primaryExchange = iexTradingStocks.getPrimaryExchange();
            double iexRealTimePrice = iexTradingStocks.getIexRealTimePrice();

            String info =   "Symbol: "+ symbol + "\n" +
                            "Company Name: " +companyName + "\n" +
                            "Primary Exchange: " + primaryExchange+ "\n" +
                            "Real Time Price: " + iexRealTimePrice;

            tvStatus.setText(info);

        }
    }
}
