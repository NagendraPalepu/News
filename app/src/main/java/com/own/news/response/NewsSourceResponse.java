package com.own.news.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsSourceResponse implements Serializable {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("statusMessage")
    private String statusMessage;
    @SerializedName("Channel")
    private ArrayList<Channels> channels;

    public int getStatusCode () {
        return statusCode;
    }

    public void setStatusCode (int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage () {
        return statusMessage;
    }

    public void setStatusMessage (String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public ArrayList<Channels> getChannels () {
        return channels;
    }

    public void setChannels (ArrayList<Channels> channels) {
        this.channels = channels;
    }

    public class Channels implements Serializable {

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("code")
        private String code;
        @SerializedName("image")
        private String imageUrl;

        public int getId () {
            return id;
        }

        public void setId (int id) {
            this.id = id;
        }

        public String getName () {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }

        public String getCode () {
            return code;
        }

        public void setCode (String code) {
            this.code = code;
        }

        public String getImageUrl () {
            return imageUrl;
        }

        public void setImageUrl (String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

}
