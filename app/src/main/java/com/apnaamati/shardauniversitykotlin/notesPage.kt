package com.apnaamati.shardauniversitykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.apnaamati.shardauniversitykotlin.R

class notesPage : AppCompatActivity() {
    lateinit var bundle : Bundle
    lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_page)
        bundle = intent.extras!!
        val link = bundle.get("link2")
        val url = "http://docs.google.com/gview?embedded=true&url=${link.toString()}"

        webView = findViewById(R.id.notesWebView)

        webView.visibility = View.VISIBLE
        webView.webViewClient = WebViewClient()
        webView.loadUrl(link.toString())
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {

        if (webView.canGoBack())
            webView.goBack()

        else
            super.onBackPressed()
    }
}