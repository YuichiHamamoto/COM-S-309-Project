package com.example.webexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web_view = findViewById(R.id.web_view);
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl("https://www.google.com/search?safe=off&q=");

        WebSettings webSettings = web_view.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (web_view.canGoBack()){
            web_view.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}