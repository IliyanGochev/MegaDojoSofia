package com.jsexperts.megadojosofia;

import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Iliyan on 17.12.2017 г..
 */

public class CustomWebViewClient extends WebViewClient {

    CustomWebViewClient() {
        super();
    }


    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Log.e("MyCertAccept", "Here we are!");
        handler.proceed(); // Ignore SSL certificate errors
    }
}
