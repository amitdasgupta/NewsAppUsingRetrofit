package com.example.amitdasgupta.newsappusingretrofit.rest;

import com.example.amitdasgupta.newsappusingretrofit.model.SorcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AMIT DAS GUPTA on 27-09-2017.
 */

public interface ApiSorceInterface {
  /*  @GET("sources")
    Call<List<Sources>> getSources(@Query("category") String category, @Query("language") String language	, @Query("country") String country);*/
    @GET("sources")
    Call<SorcesResponse> getSourceResponse(@Query("category") String category,@Query("language") String language,@Query("country") String country);
}
