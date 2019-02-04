package com.own.news.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.own.news.R;
import com.own.news.network.GetDataTask;
import com.own.news.response.NewsSourceResponse;
import com.own.news.ui.NewsDetailsActivity;
import com.own.news.utils.AppUtils;
import com.own.news.utils.ImageUtils;

import java.util.ArrayList;

public class CountryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noDataAvailable;
    private Context context;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        context = getActivity ();
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from (getActivity ()).inflate (R.layout.fragment_layout, container, false);

        recyclerView = view.findViewById (R.id.recycler_view);
        progressBar = view.findViewById (R.id.progressBar);
        noDataAvailable = view.findViewById (R.id.noDataAvailable);

        if (AppUtils.launchMode) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager (context, 2);
            recyclerView.setLayoutManager (mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager (context, 1);
            recyclerView.setLayoutManager (mLayoutManager);
        }

        displayUpdate (1);
        getNewChannelsData ();
        return view;
    }


    private void getNewChannelsData () {
        GetDataTask getDataTask = new GetDataTask (context, "country.json");
        getDataTask.setCallback (new GetDataTask.Callback () {
            @Override
            public void onPostExecute (String result, Object resultObject) {
                NewsSourceResponse newsSourceResponse = new Gson ().fromJson (result, NewsSourceResponse.class);
                if (newsSourceResponse != null) {
                    if (newsSourceResponse.getStatusCode () == 200) {
                        if (newsSourceResponse.getChannels () != null) {
                            if (newsSourceResponse.getChannels ().size () > 0) {
                                displayUpdate (2);
                                ChannelsAdapter channelsAdapter = new ChannelsAdapter (newsSourceResponse.getChannels (), newsSourceResponse.getType ());
                                recyclerView.setAdapter (channelsAdapter);
                            } else {
                                displayUpdate (3);
                            }
                        } else {
                            displayUpdate (3);
                        }
                    } else {
                        displayUpdate (3);
                    }
                } else {
                    displayUpdate (3);
                }

            }
        }).execute ();
    }


    class ChannelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final ArrayList<NewsSourceResponse.Channels> albumList;
        private final String type;

        class MyViewHolder extends RecyclerView.ViewHolder {
            final TextView title;
            final ImageView thumbnail;
            final CardView layout;

            MyViewHolder (View view) {
                super (view);
                title = view.findViewById (R.id.name);
                thumbnail = view.findViewById (R.id.ImageView);
                layout = view.findViewById (R.id.layout);

                thumbnail.setScaleType (ImageView.ScaleType.FIT_XY);
                title.setTypeface (AppUtils.getOpenSansRegular (context));

                int width = AppUtils.getCalculatedWidth (context, 100);


                GridLayoutManager.LayoutParams grLayoutParams = (GridLayoutManager.LayoutParams) layout.getLayoutParams ();
                grLayoutParams.width = (width - (3 * AppUtils.getCalculatedWidth (context, 1))) / 2;
                grLayoutParams.height = (width - (3 * AppUtils.getCalculatedWidth (context, 1))) / 2;
                grLayoutParams.setMargins (AppUtils.getCalculatedWidth (context, 1), 0, AppUtils.getCalculatedWidth (context, 1), 0);
                layout.setLayoutParams (grLayoutParams);

            }
        }


        class MyViewHolder2 extends RecyclerView.ViewHolder {
            final TextView title;
            final ImageView thumbnail;
            final CardView layout;

            MyViewHolder2 (View view) {
                super (view);
                title = view.findViewById (R.id.name);
                thumbnail = view.findViewById (R.id.imageView);
                layout = view.findViewById (R.id.layout);
                title.setTypeface (AppUtils.getOpenSansRegular (context));
                title.setTextColor (getResources ().getColor (R.color.black));
            }
        }


        ChannelsAdapter (ArrayList<NewsSourceResponse.Channels> albumList, String t) {
            this.albumList = albumList;
            this.type = t;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {


            if (AppUtils.launchMode) {
                View itemView = LayoutInflater.from (parent.getContext ())
                        .inflate (R.layout.source_file, parent, false);

                return new MyViewHolder (itemView);
            } else {
                View itemView1 = LayoutInflater.from (parent.getContext ())
                        .inflate (R.layout.source_new, parent, false);

                return new MyViewHolder2 (itemView1);
            }


        }

        @Override
        public void onBindViewHolder (@NonNull final RecyclerView.ViewHolder holder, int position) {

            if (holder instanceof MyViewHolder) {
                NewsSourceResponse.Channels channels = albumList.get (holder.getAdapterPosition ());

                if (channels != null) {
                    ImageUtils.loadSVGImage ((Activity) context, channels.getImageUrl (), ((MyViewHolder) holder).thumbnail, ImageUtils.getRequestOptions (R.drawable.ic_launcher_background));
                    ((MyViewHolder) holder).title.setText (channels.getName ());


                    ((MyViewHolder) holder).layout.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick (View v) {
                            Intent intent = new Intent (context, NewsDetailsActivity.class);
                            intent.putExtra ("Object", albumList.get (holder.getAdapterPosition ()));
                            intent.putExtra ("type", type);
                            startActivity (intent);
                        }
                    });
                }
            } else if (holder instanceof MyViewHolder2) {
                NewsSourceResponse.Channels channels = albumList.get (holder.getAdapterPosition ());

                if (channels != null) {
                    ImageUtils.loadImage (channels.getImageUrl (), ((MyViewHolder2) holder).thumbnail, ImageUtils.getRequestOptions (R.drawable.ic_launcher_background));
                    ((MyViewHolder2) holder).title.setText (channels.getName ());

                    ((MyViewHolder2) holder).layout.setOnClickListener (new View.OnClickListener () {
                        @Override
                        public void onClick (View v) {
                            Intent intent = new Intent (context, NewsDetailsActivity.class);
                            intent.putExtra ("Object", albumList.get (holder.getAdapterPosition ()));
                            intent.putExtra ("type", type);
                            startActivity (intent);
                        }
                    });
                }
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
