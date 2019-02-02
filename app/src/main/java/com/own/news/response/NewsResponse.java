package com.own.news.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsResponse implements Serializable {

    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private ArrayList<Articles> articlesArrayList;

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public int getTotalResults () {
        return totalResults;
    }

    public void setTotalResults (int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Articles> getArticlesArrayList () {
        return articlesArrayList;
    }

    public void setArticlesArrayList (ArrayList<Articles> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
    }

    class Articles implements Serializable {

        @SerializedName("source")
        private Source source;

        @SerializedName("author")
        private String author;

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("url")
        private String url;

        @SerializedName("urlToImage")
        private String urlToImage;

        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("content")
        private String content;

        public Source getSource () {
            return source;
        }

        public void setSource (Source source) {
            this.source = source;
        }

        public String getAuthor () {
            return author;
        }

        public void setAuthor (String author) {
            this.author = author;
        }

        public String getTitle () {
            return title;
        }

        public void setTitle (String title) {
            this.title = title;
        }

        public String getDescription () {
            return description;
        }

        public void setDescription (String description) {
            this.description = description;
        }

        public String getUrl () {
            return url;
        }

        public void setUrl (String url) {
            this.url = url;
        }

        public String getUrlToImage () {
            return urlToImage;
        }

        public void setUrlToImage (String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt () {
            return publishedAt;
        }

        public void setPublishedAt (String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent () {
            return content;
        }

        public void setContent (String content) {
            this.content = content;
        }

        class Source implements Serializable {

            @SerializedName("id")
            String id;
            @SerializedName("name")
            String name;

            public String getId () {
                return id;
            }

            public void setId (String id) {
                this.id = id;
            }
        }
    }
}
