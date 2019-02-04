package com.own.news.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AppUtils {

    public static boolean launchMode = false;
    public static final String DEVELOPER_KEY = "AIzaSyCG25IwSEZcJuF5Te7kko9XawkHaEJ48Ws";


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


    public static void setDynamicDimensions (View view, Context context, double width, double height) {

        if (view.getLayoutParams () instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (height != 0) {
                layoutParams.height = AppUtils.getCalculatedHeight (context, height);
            }
            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (height != 0) {
                layoutParams.height = AppUtils.getCalculatedHeight (context, height);
            }

            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof CardView.LayoutParams) {
            CardView.LayoutParams layoutParams = (CardView.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (height != 0) {
                layoutParams.height = AppUtils.getCalculatedHeight (context, height);
            }

            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (height != 0) {
                layoutParams.height = AppUtils.getCalculatedHeight (context, height);
            }

            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (height != 0) {
                layoutParams.height = AppUtils.getCalculatedHeight (context, height);
            }

            view.setLayoutParams (layoutParams);
        }

    }


    public static void setDynamicSquareDimensions (View view, Context context, double width) {

        if (view.getLayoutParams () instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (width != 0) {
                layoutParams.height = AppUtils.getCalculatedWidth (context, width);
            }
            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (width != 0) {
                layoutParams.height = AppUtils.getCalculatedWidth (context, width);
            }

            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams ();
            if (width != 0) {
                layoutParams.width = AppUtils.getCalculatedWidth (context, width);
            }
            if (width != 0) {
                layoutParams.height = AppUtils.getCalculatedWidth (context, width);
            }

            view.setLayoutParams (layoutParams);
        }

    }


    public static int getCalculatedHeight (Context context, double percentage) {
        DisplayMetrics displaymetrics = context.getResources ().getDisplayMetrics ();
        return (int) (displaymetrics.heightPixels * (percentage / 100));
    }

    public static int getCalculatedWidth (Context context, double percentage) {
        DisplayMetrics displaymetrics = context.getResources ().getDisplayMetrics ();
        return (int) (displaymetrics.widthPixels * (percentage / 100));
    }


    public static void setDynamicMargins (View view, Context context, double left, double top, double right, double bottom) {

        if (view.getLayoutParams () instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams ();
            layoutParams.setMargins (getCalculatedWidth (context, left), getCalculatedHeight (context, top), getCalculatedWidth (context, right), getCalculatedHeight (context, bottom));
            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams ();
            layoutParams.setMargins (getCalculatedWidth (context, left), getCalculatedHeight (context, top), getCalculatedWidth (context, right), getCalculatedHeight (context, bottom));
            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof CardView.LayoutParams) {
            CardView.LayoutParams layoutParams = (CardView.LayoutParams) view.getLayoutParams ();
            layoutParams.setMargins (getCalculatedWidth (context, left), getCalculatedHeight (context, top), getCalculatedWidth (context, right), getCalculatedHeight (context, bottom));
            view.setLayoutParams (layoutParams);
        } else if (view.getLayoutParams () instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams ();
            layoutParams.setMargins (getCalculatedWidth (context, left), getCalculatedHeight (context, top), getCalculatedWidth (context, right), getCalculatedHeight (context, bottom));
            view.setLayoutParams (layoutParams);
        }

    }
}
