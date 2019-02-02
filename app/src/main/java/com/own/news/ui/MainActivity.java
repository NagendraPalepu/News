package com.own.news.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.own.news.R;
import com.own.news.network.GetDataTask;
import com.own.news.response.NewsSourceResponse;
import com.own.news.utils.AppUtils;
import com.own.news.utils.GridSpacingItemDecoration;
import com.own.news.utils.ImageUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        TextView title = findViewById (R.id.source);
        title.setTypeface (AppUtils.getOpenSansSemiBold (this));

        recyclerView = findViewById (R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager (this, 2);
        recyclerView.setLayoutManager (mLayoutManager);
        recyclerView.addItemDecoration (new GridSpacingItemDecoration (2, dpToPx (), true));
        recyclerView.setItemAnimator (new DefaultItemAnimator ());


        getNewChannelsData ();
    }


    private void getNewChannelsData () {
        GetDataTask getDataTask = new GetDataTask (this, "news_home.json");
        getDataTask.setCallback (new GetDataTask.Callback () {
            @Override
            public void onPostExecute (String result, Object resultObject) {
                NewsSourceResponse newsSourceResponse = new Gson ().fromJson (result, NewsSourceResponse.class);
                if (newsSourceResponse != null) {
                    if (newsSourceResponse.getStatusCode () == 200) {
                        if (newsSourceResponse.getChannels () != null) {
                            if (newsSourceResponse.getChannels ().size () > 0) {
                                ChannelsAdapter channelsAdapter = new ChannelsAdapter (newsSourceResponse.getChannels ());
                                recyclerView.setAdapter (channelsAdapter);
                            }
                        }
                    }
                }

            }
        }).execute ();
    }

    private int dpToPx () {
        Resources r = getResources ();
        return Math.round (TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics ()));
    }


    public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.MyViewHolder> {

        private ArrayList<NewsSourceResponse.Channels> albumList;

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView thumbnail;

            MyViewHolder (View view) {
                super (view);
                title = view.findViewById (R.id.name);
                thumbnail = view.findViewById (R.id.ImageView);

                title.setTypeface (AppUtils.getOpenSansRegular (MainActivity.this));
            }
        }


        ChannelsAdapter (ArrayList<NewsSourceResponse.Channels> albumList) {
            this.albumList = albumList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from (parent.getContext ())
                    .inflate (R.layout.source_file, parent, false);

            return new MyViewHolder (itemView);
        }

        @Override
        public void onBindViewHolder (@NonNull final MyViewHolder holder, int position) {

            NewsSourceResponse.Channels channels = albumList.get (holder.getAdapterPosition ());

            if (channels != null) {
                ImageUtils.loadImage (channels.getImageUrl (), holder.thumbnail, ImageUtils.getRequestOptions (R.drawable.ic_launcher_background));
                holder.title.setText (channels.getName ());
            }

        }


        @Override
        public int getItemCount () {
            return albumList.size ();
        }
    }
}
