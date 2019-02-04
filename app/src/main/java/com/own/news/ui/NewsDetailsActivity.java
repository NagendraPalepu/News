package com.own.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.own.news.R;
import com.own.news.network.API;
import com.own.news.network.RetrofitHelper;
import com.own.news.response.NewsResponse;
import com.own.news.response.NewsSourceResponse;
import com.own.news.utils.AppUtils;
import com.own.news.utils.CircleImageView;
import com.own.news.utils.ImageUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends Activity {

    private RecyclerView recyclerView;
    private NewsSourceResponse.Channels channels;
    private ProgressBar progressBar;
    private TextView noDataAvailable;
    private String type;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        if (getIntent ().getExtras () != null) {
            try {
                channels = (NewsSourceResponse.Channels) getIntent ().getExtras ().getSerializable ("Object");
            } catch (Exception e) {
                e.printStackTrace ();
            }

            try {
                type = getIntent ().getExtras ().getString ("type");
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }

        TextView title = findViewById (R.id.source);
        ImageView back = findViewById (R.id.back);
        CircleImageView logo = findViewById (R.id.logo);

        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
            }
        });


        if (channels != null) {
            title.setText (channels.getName ());
            ImageUtils.loadImage (channels.getImageUrl (), logo, ImageUtils.getRequestOptions (R.drawable.ic_launcher_background));
        }
        title.setTypeface (AppUtils.getOpenSansSemiBold (this));

        recyclerView = findViewById (R.id.recycler_view);
        progressBar = findViewById (R.id.progressBar);
        noDataAvailable = findViewById (R.id.noDataAvailable);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager (mLayoutManager);
        recyclerView.setItemAnimator (new DefaultItemAnimator ());


        displayUpdate (1);
        getNewsData ();
    }

    private void getNewsData () {
        if (channels == null) {
            return;
        }

        String parentUrl = API.APP_SERVER_URL_REST;

        Call<NewsResponse> newsResponseCall;
        if (type.equalsIgnoreCase ("co")) {
            newsResponseCall = RetrofitHelper.getCommonApi (this, parentUrl).getLatestCountryNews (channels.getCode (), "d7ed7e03d66847d2ae8f2339e3540ed0");
        } else {
            newsResponseCall = RetrofitHelper.getCommonApi (this, parentUrl).getLatestNews (channels.getCode (), "d7ed7e03d66847d2ae8f2339e3540ed0");
        }
        newsResponseCall.enqueue (new Callback<NewsResponse> () {
            @Override
            public void onResponse (@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body ();
                if (newsResponse != null) {
                    if (newsResponse.getStatus () != null) {
                        if (newsResponse.getStatus ().equalsIgnoreCase ("ok")) {

                            if (newsResponse.getArticlesArrayList ().size () > 0) {
                                NewsAdapter newsAdapter = new NewsAdapter (newsResponse.getArticlesArrayList ());
                                recyclerView.setAdapter (newsAdapter);

                                displayUpdate (2);
                            } else {
                                displayUpdate (3);
                            }

                        } else {
                            displayUpdate (3);
                        }
                    }
                } else {
                    displayUpdate (3);
                }
            }

            @Override
            public void onFailure (@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                displayUpdate (3);
            }
        });
    }


    public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

        private ArrayList<NewsResponse.Articles> albumList;

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title, description;
            ImageView thumbnail;
            RelativeLayout layout;

            MyViewHolder (View view) {
                super (view);
                title = view.findViewById (R.id.title);
                description = view.findViewById (R.id.description);
                thumbnail = view.findViewById (R.id.sourceImage);
                layout = view.findViewById (R.id.layout);

                thumbnail.setScaleType (ImageView.ScaleType.FIT_XY);
                AppUtils.setDynamicDimensions (layout, NewsDetailsActivity.this, 96, 30);


                AppUtils.setDynamicMargins (layout, NewsDetailsActivity.this, 2, 0.5, 2, 0.5);

                title.setTypeface (AppUtils.getOpenSansRegular (NewsDetailsActivity.this));
                description.setTypeface (AppUtils.getOpenSansRegular (NewsDetailsActivity.this));
            }
        }


        NewsAdapter (ArrayList<NewsResponse.Articles> albumList) {
            this.albumList = albumList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from (parent.getContext ())
                    .inflate (R.layout.news_details, parent, false);

            return new NewsAdapter.MyViewHolder (itemView);
        }

        @Override
        public void onBindViewHolder (@NonNull final NewsAdapter.MyViewHolder holder, int position) {
            NewsResponse.Articles articles = albumList.get (holder.getAdapterPosition ());
            if (articles != null) {

                ImageUtils.loadImage (articles.getUrlToImage (), holder.thumbnail, ImageUtils.getRequestOptions (R.drawable.ic_launcher_background));
                holder.title.setText (articles.getTitle ());
                holder.description.setText (articles.getDescription ());


                holder.layout.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick (View v) {
                        Intent intent = new Intent (NewsDetailsActivity.this, WebViewActivity.class);
                        intent.putExtra ("object", albumList.get (holder.getAdapterPosition ()));
                        startActivity (intent);
                    }
                });
            }

        }


        @Override
        public int getItemCount () {
            return albumList.size ();
        }
    }


    private void displayUpdate (int i) {
        switch (i) {
            case 1:
                noDataAvailable.setVisibility (View.GONE);
                progressBar.setVisibility (View.VISIBLE);
                recyclerView.setVisibility (View.GONE);
                break;
            case 2:
                noDataAvailable.setVisibility (View.GONE);
                progressBar.setVisibility (View.GONE);
                recyclerView.setVisibility (View.VISIBLE);
                break;
            case 3:
                noDataAvailable.setVisibility (View.VISIBLE);
                progressBar.setVisibility (View.GONE);
                recyclerView.setVisibility (View.GONE);
                break;
        }
    }
}
