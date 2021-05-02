package com.example.w3schools;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        WebView webView = findViewById(R.id.webview);
        webView.loadUrl("https://www.w3schools.com");
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.isHardwareAccelerated();
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new WebViewClient());

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));
                myRequest.allowScanningByMediaScanner();
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager Mymanager = (DownloadManager)getApplicationContext().getSystemService(DOWNLOAD_SERVICE);
                Mymanager.enqueue(myRequest);
                Toast.makeText(getApplicationContext(),"File is Downloading...",Toast.LENGTH_LONG).show();
            }
        });

        webView.setOnKeyListener((View.OnKeyListener) (v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                        //return true;
                    }
                    return MainActivity.super.onKeyDown(keyCode, event);
                }
            }
            return true;
        });

        }
    }