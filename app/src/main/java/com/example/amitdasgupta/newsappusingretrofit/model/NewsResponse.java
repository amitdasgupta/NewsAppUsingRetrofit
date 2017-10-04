package com.example.amitdasgupta.newsappusingretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AMIT DAS GUPTA on 25-09-2017.
 */

public class NewsResponse {
    @SerializedName("status")
    private String source;
    @SerializedName("source")
    private String status;
    @SerializedName("sortBy")
    private String sort;
    @SerializedName("articles")
    private List<News> articles;

    public NewsResponse() {
    }

    public void setSource(String source) {

        this.source = source;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }

    public String getSource() {

        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getSort() {
        return sort;
    }

    public List<News> getArticles() {
        return articles;
    }

    public NewsResponse(String source, String status, String sort, List<News> articles) {

        this.source = source;
        this.status = status;
        this.sort = sort;
        this.articles = articles;
    }
}
