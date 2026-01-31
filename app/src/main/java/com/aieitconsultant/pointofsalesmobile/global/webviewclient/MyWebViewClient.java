package com.aieitconsultant.pointofsalesmobile.global.webviewclient;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aieitconsultant.pointofsalesmobile.global.url.URL;

public class MyWebViewClient extends WebViewClient {

    private final URL url = new URL();

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        // Must declare with this example only!!! Eg. (google.com)
        if (this.url.getName().equals(request.getUrl().getHost())) {
            // This is your website, so don't override. Let your WebView load the
            // page.
            return false;
        }
        // Otherwise, the link isn't for a page on your site, so launch another
        // Activity that handles URLs.
//        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//        startActivity(intent);
        return true;
    }
}
