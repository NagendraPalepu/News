package com.own.news.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoDataResponse implements Serializable {

    @SerializedName ("provider_name")
    private String provider_name;

    @SerializedName ("thumbnail_width")
    private int thumbnail_width;

    @SerializedName ("thumbnail_height")
    private int thumbnail_height;

    @SerializedName ("title")
    private String title;

    @SerializedName ("html")
    private String html;

    @SerializedName ("type")
    private String type;

    @SerializedName ("provider_url")
    private String provider_url;

    @SerializedName ("height")
    private int height;

    @SerializedName ("width")
    private int width;

    @SerializedName ("author_url")
    private String author_url;

    @SerializedName ("thumbnail_url")
    private String thumbnail_url;

    @SerializedName ("author_name")
    private String author_name;

    @SerializedName ("version")
    private String version;

    public String getProvider_name () {
        return provider_name;
    }

    public void setProvider_name (String provider_name) {
        this.provider_name = provider_name;
    }

    public int getThumbnail_width () {
        return thumbnail_width;
    }

    public void setThumbnail_width (int thumbnail_width) {
        this.thumbnail_width = thumbnail_width;
    }

    public int getThumbnail_height () {
        return thumbnail_height;
    }

    public void setThumbnail_height (int thumbnail_height) {
        this.thumbnail_height = thumbnail_height;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getHtml () {
        return html;
    }

    public void setHtml (String html) {
        this.html = html;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getProvider_url () {
        return provider_url;
    }

    public void setProvider_url (String provider_url) {
        this.provider_url = provider_url;
    }

    public int getHeight () {
        return height;
    }

    public void setHeight (int height) {
        this.height = height;
    }

    public int getWidth () {
        return width;
    }

    public void setWidth (int width) {
        this.width = width;
    }

    public String getAuthor_url () {
        return author_url;
    }

    public void setAuthor_url (String author_url) {
        this.author_url = author_url;
    }

    public String getThumbnail_url () {
        return thumbnail_url;
    }

    public void setThumbnail_url (String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getAuthor_name () {
        return author_name;
    }

    public void setAuthor_name (String author_name) {
        this.author_name = author_name;
    }

    public String getVersion () {
        return version;
    }

    public void setVersion (String version) {
        this.version = version;
    }
}
