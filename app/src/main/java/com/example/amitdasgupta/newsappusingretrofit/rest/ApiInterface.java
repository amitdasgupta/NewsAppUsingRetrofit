package com.example.amitdasgupta.newsappusingretrofit.rest;

import com.example.amitdasgupta.newsappusingretrofit.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AMIT DAS GUPTA on 25-09-2017.
 */

public interface ApiInterface {
    @GET("articles")
    Call<NewsResponse> getSourceResponse(@Query("source") String source, @Query("apiKey") String apiKey, @Query("sortBy") String sortBy);
}
