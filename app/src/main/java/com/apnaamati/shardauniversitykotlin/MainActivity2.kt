package com.apnaamati.shardauniversitykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.apnaamati.shardauniversitykotlin.adapters.recyclerAdapter
import com.apnaamati.shardauniversitykotlin.adapters.videoAdapter
import com.apnaamati.shardauniversitykotlin.models.notesModel
import com.apnaamati.shardauniversitykotlin.models.videoModel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity2 : AppCompatActivity() {
    lateinit var bundle : Bundle
    lateinit var recylerView: RecyclerView
    lateinit var webView : WebView
    private var ref: DatabaseReference? = null
    lateinit var viewPager2: ViewPager2
    var videoList = ArrayList<videoModel>()
    var notesList = ArrayList<notesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //getting intent
        bundle = intent.extras!!
        val tittle = bundle.get("tittle")
        val link = bundle.get("link")

        supportActionBar?.setTitle(title.toString())

        recylerView = findViewById(R.id.mRecyclerView)
        webView = findViewById(R.id.webView)
        recylerView.layoutManager = LinearLayoutManager(this)

        viewPager2 = findViewById(R.id.viewPager2)
        videoList = arrayListOf<videoModel>()


        //open the page as per intent message
        if (tittle!!.equals("Notes")){
            recylerView.visibility = View.VISIBLE
            viewPager2.visibility = View.GONE
            ref = FirebaseDatabase.getInstance().getReference("Notes")
            ref!!.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(notes in snapshot.children){
                           val list = notes.getValue(notesModel::class.java)
                            notesList.add(list!!)
                        }
                    }
                    val adapter = recyclerAdapter(notesList,this@MainActivity2 )
                    recylerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
       else if(tittle!!.equals("Videos")){
           viewPager2.visibility = View.VISIBLE
            ref = FirebaseDatabase.getInstance().getReference("Videos")
            ref!!.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){

                        for(videos in snapshot.children){
                            val list = videos.getValue(videoModel::class.java)
                            videoList.add(list!!)
                        }
                    }
                    videoList.shuffle(Random(System.currentTimeMillis()))
                    val adapter = videoAdapter(videoList,this@MainActivity2 )
                    viewPager2.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            }

        else{
            webView.visibility = View.VISIBLE
            webView.webViewClient = WebViewClient()
            webView.loadUrl(link.toString())
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
        }
    }
    override fun onBackPressed() {

        if (webView.canGoBack())
            webView.goBack()

             else
            super.onBackPressed()
    }
}