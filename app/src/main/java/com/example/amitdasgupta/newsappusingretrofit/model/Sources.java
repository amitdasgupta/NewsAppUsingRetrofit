package com.example.amitdasgupta.newsappusingretrofit.model;

import com.example.amitdasgupta.newsappusingretrofit.smaller_models.UrlToLogos;

/**
 * Created by AMIT DAS GUPTA on 27-09-2017.
 */

public class Sources {
    String id;
    String name;

    public Sources() {
    }

    String description;
    String url;
    String category;
    String language;
    String country;
    UrlToLogos urlsToLogos;
    String sortByAvailable[];

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUrlsToLogos(UrlToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public void setSortByAvailable(String[] sortByAvailable) {
        this.sortByAvailable = sortByAvailable;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public UrlToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public String[] getSortByAvailable() {
        return sortByAvailable;
    }

    public Sources(String id, String name, String description, String url, String category, String language, String country, UrlToLogos urlsToLogos, String[] sortByAvailable) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
        this.urlsToLogos = urlsToLogos;
        this.sortByAvailable = sortByAvailable;
    }
}
