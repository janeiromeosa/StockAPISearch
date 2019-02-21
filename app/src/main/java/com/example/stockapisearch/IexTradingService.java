package com.example.stockapisearch;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

public interface IexTradingService {
    @GET("1.0/stock/{username}/quote")
    Call<List<IexTradingRepo>> getRepos(@Path("username")String userName);


}
