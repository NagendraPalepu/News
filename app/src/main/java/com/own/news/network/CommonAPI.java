package com.own.news.network;


import com.own.news.response.NewsResponse;
import com.own.news.response.VideoDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommonAPI {

    @GET(API.Everything)
    Call<NewsResponse> getLatestNews (@Query ("sources") String sources, @Query("apiKey") String apiKey);

    @GET(API.TopHeadlines)
    Call<NewsResponse> getLatestCountryNews (@Query ("country") String sources, @Query("apiKey") String apiKey);


    @GET(API.Oembed)
    Call<VideoDataResponse> getVideoData (@Query ("url") String sources, @Query("format") String apiKey);
}
