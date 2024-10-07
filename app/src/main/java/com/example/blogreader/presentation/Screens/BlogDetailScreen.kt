package com.example.blogreader.presentation.Screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder

@Composable
fun BlogDetailScreen(blogUrl: String) {
    CustomWebView(blogUrl)
}
@Composable
fun CustomWebView(blogUrl: String){
    val decodedUrl = URLDecoder.decode(blogUrl, "UTF-8")
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(decodedUrl)
        }
    }, update = { webView ->
        if(webView.url!=decodedUrl)
            webView.loadUrl(decodedUrl)
    })
}
