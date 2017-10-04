package com.example.amitdasgupta.newsappusingretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMIT DAS GUPTA on 27-09-2017.
 */

public class SorcesResponse {
    String status;
    @SerializedName("sources")
    private List<Sources> results;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(List<Sources> results) {
        this.results = results;
    }

    public String getStatus() {

        return status;
    }

    public List<Sources> getResults() {
        return results;
    }

    public SorcesResponse(String status, List<Sources> results) {

        this.status = status;
        this.results = results;
    }
}
