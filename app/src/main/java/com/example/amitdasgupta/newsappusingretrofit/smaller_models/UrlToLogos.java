package com.example.amitdasgupta.newsappusingretrofit.smaller_models;

/**
 * Created by AMIT DAS GUPTA on 27-09-2017.
 */

public class UrlToLogos {

    String small;
    String medium;

    public void setSmall(String small) {
        this.small = small;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public UrlToLogos(String small, String medium, String large) {

        this.small = small;
        this.medium = medium;
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }

    String large;
}
