package com.own.news.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.own.news.R;

import java.io.InputStream;

public class ImageUtils {


    public static void loadImage (String url, ImageView imageView, RequestOptions requestOptions) {
        if (TextUtils.isEmpty (url)) {
            Glide.with (imageView.getContext ())
                    .applyDefaultRequestOptions (requestOptions)
                    .load (R.drawable.ic_launcher_background)
                    .into (imageView);
            return;
        }


        Glide.with (imageView.getContext ())
                .applyDefaultRequestOptions (requestOptions)
                .load (url)
                .into (imageView);


    }


    public static void loadSVGImage (Activity context, String url, ImageView imageView, RequestOptions requestOptions) {
        if (TextUtils.isEmpty (url)) {
            Glide.with (imageView.getContext ())
                    .applyDefaultRequestOptions (requestOptions)
                    .load (R.drawable.ic_launcher_background)
                    .into (imageView);
            return;
        }


        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(context)
                .getRequestBuilder();

        requestBuilder
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(imageView);


    }


    public static RequestOptions getRequestOptions (int placeHolder) {

        return new RequestOptions ()
                .error (R.drawable.ic_launcher_background)
                .placeholder (placeHolder);
    }
}
