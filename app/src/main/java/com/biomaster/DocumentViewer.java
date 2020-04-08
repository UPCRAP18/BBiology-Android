package com.biomaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DocumentViewer extends AppCompatActivity {
    private String path = "";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        ActionBar aBar = getSupportActionBar();
        if (aBar != null){
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setTitle(getResources().getString(R.string.headerDocuments));
        }
        if(getIntent().getStringExtra("path") != null){
            path = getIntent().getStringExtra("path");
        }

        final ProgressDialog pBar = ProgressDialog.show(this, "", "Cargando...", true);

        WebView webView = findViewById(R.id.webView);
        String url = "https://docs.google.com/viewer?url=".concat(path);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if (pBar != null &&  pBar.isShowing()){
                    pBar.dismiss();
                }
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl(url);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
