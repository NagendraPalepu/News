package com.own.news.utils;

import android.content.Context;
import android.graphics.Typeface;

public class AppUtils {


    public static Typeface getOpenSansItalic (Context context) {
        return Typeface.createFromAsset (context.getResources ().getAssets (), "opensans_italic.ttf");
    }

    public static Typeface getOpenSansRegular (Context context) {
        return Typeface.createFromAsset (context.getResources ().getAssets (), "opensans_regular.ttf");
    }

    public static Typeface getOpenSansSemiBold (Context context) {
        return Typeface.createFromAsset (context.getResources ().getAssets (), "opensans_semibold.ttf");
    }

    public static Typeface getOpenSansLight (Context context) {
        return Typeface.createFromAsset (context.getResources ().getAssets (), "opensans_light.ttf");
    }
}
