package com.own.news.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.own.news.R;

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


    public static RequestOptions getRequestOptions (int placeHolder) {

        return new RequestOptions ()
                .error (R.drawable.ic_launcher_background)
                .placeholder (placeHolder);
    }
}
