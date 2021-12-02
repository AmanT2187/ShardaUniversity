package com.apnaamati.shardauniversitykotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apnaamati.shardauniversitykotlin.R
import com.apnaamati.shardauniversitykotlin.models.videoModel

class videoAdapter(private val videoList: ArrayList<videoModel>, private val context: Context):
    RecyclerView.Adapter<videoAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): videoAdapter.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_file,parent,false)
        return videoAdapter.myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: videoAdapter.myViewHolder, position: Int) {
        holder.webView.settings.javaScriptEnabled = true
        holder.webView.webViewClient = WebViewClient()
        val videos = videoList[position]

        holder.webView.loadUrl(videos.url.toString())
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val webView : WebView = itemView.findViewById(R.id.webViewVideo)
    }
}