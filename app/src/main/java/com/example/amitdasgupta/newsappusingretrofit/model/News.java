package com.example.amitdasgupta.newsappusingretrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMIT DAS GUPTA on 25-09-2017.
 */

public class News {
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urltoimage;
    @SerializedName("publishedAt")
    private String publishedAt;

}
