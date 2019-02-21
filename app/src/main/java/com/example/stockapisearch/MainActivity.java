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
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY)).
                connectTimeout(20, TimeUnit.SECONDS).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(Constants.BASE_URL).client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        IexTradingService iexTradingService = retrofit.create(IexTradingService.class);

        iexTradingService.getRepos(etStock.getText().toString()).enqueue(new Callback<List<IexTradingRepo>>() {
            @Override
            public void onResponse(Call<List<IexTradingRepo>> call, Response<List<IexTradingRepo>> response) {


                String symbol = iexTradingStocks.getSymbol();
                String companyName = iexTradingStocks.getCompanyName();
                String primaryExchange = iexTradingStocks.getPrimaryExchange();
                double iexRealTimePrice = iexTradingStocks.getIexRealTimePrice();

                String info =   "Symbol: "+ symbol + "\n" +
                        "Company Name: " + companyName + "\n" +
                        "Primary Exchange: " + primaryExchange + "\n" +
                        "Real Time Price: " + iexRealTimePrice;

                tvStatus.setText(info);
            }

            @Override
            public void onFailure(Call<List<IexTradingRepo>> call, Throwable t) {

            }
        });

    }//searchclicked

}//do a recycler view
