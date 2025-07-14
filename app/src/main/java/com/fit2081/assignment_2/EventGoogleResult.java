package com.fit2081.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EventGoogleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_google_result);

        // Get the event name from the intent
        String eventName = getIntent().getStringExtra("eventName");

        // Build the URL for Google search
        String url = "https://www.google.com/search?q=" + eventName;

        // Find the WebView and load the URL
        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Ensures the URL loads in the WebView
        webView.loadUrl(url);
    }
}
