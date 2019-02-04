package com.own.news.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.own.news.R;
import com.own.news.response.NewsResponse;
import com.own.news.utils.AppUtils;

public class WebViewActivity extends Activity {

    private NewsResponse.Articles articles;
    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.web_view);
        if (getIntent ().getExtras () != null) {
            articles = (NewsResponse.Articles) getIntent ().getExtras ().getSerializable ("object");
        }

        TextView title = findViewById (R.id.source);
        progressBar = findViewById (R.id.progressBar);

        if (articles != null) {
            title.setText (articles.getSource ().getName ());
        }
        title.setTypeface (AppUtils.getOpenSansSemiBold (this));

        WebView webView = findViewById (R.id.webView);
        WebSettings webSettings = webView.getSettings ();
        webSettings.setJavaScriptEnabled (true);
        if (articles != null) {
            webView.loadUrl (articles.getUrl ());
        }

        webView.setWebViewClient (new WebViewClient () {
            @Override
            public void onPageStarted (WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility (View.VISIBLE);
            }

            @Override
            public void onPageFinished (WebView view, String url) {
                progressBar.setVisibility (View.GONE);
            }
        });

    }
}
