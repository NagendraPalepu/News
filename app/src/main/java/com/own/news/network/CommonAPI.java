package com.own.news.network;


import com.own.news.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommonAPI {

    @GET(API.Everything)
    Call<NewsResponse> getLatestNews (@Query("sources") String source, @Query("apiKey") String apiKey);

}
