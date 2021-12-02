package com.apnaamati.shardauniversitykotlin

import android.app.ProgressDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apnaamati.shardauniversitykotlin.adapters.collectionsAdapter
import com.apnaamati.shardauniversitykotlin.adapters.sliderAdapter
import com.google.firebase.database.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() {
    lateinit var sliderView: SliderView
    var ref: DatabaseReference? = null
    var sliderList = ArrayList<slider>()
    var collectionList = ArrayList<collectionsModel>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderView = findViewById(R.id.imageSlider)
        sliderList = arrayListOf<slider>()

        recyclerView = findViewById(R.id.recyclerItems)
        collectionList = arrayListOf<collectionsModel>()

        val manager: RecyclerView.LayoutManager = GridLayoutManager(this@MainActivity, 2)
        recyclerView.layoutManager = manager

        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.setScrollTimeInSec(3)
        sliderView.startAutoCycle()

        fecthFun()
        fecthImages()

    }
    private fun fecthFun() {
        val ref = FirebaseDatabase.getInstance().getReference("Funs")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (items in snapshot.children) {
                        val list = items.getValue(collectionsModel::class.java)
                        collectionList.add(list!!)
                    }
                }
                val adapter = collectionsAdapter(collectionList, this@MainActivity!!)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun fecthImages() {
        ref = FirebaseDatabase.getInstance().getReference("sliders")
        ref!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (slider1 in snapshot.children) {
                        val list = slider1.getValue(slider::class.java)
                        sliderList.add(list!!)
                    }
                }
                val adapter = sliderAdapter(sliderList, this@MainActivity)
                sliderView.setSliderAdapter(adapter)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_fav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
        R.id.btnFav -> {
            Toast.makeText(this@MainActivity, "Your Favourite", Toast.LENGTH_SHORT).show()
            true
        }
            R.id.btnAbout -> {
                Toast.makeText(this@MainActivity, "This app is designed and\n developed for Sharda Universtiy", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.btnVersion -> {
                Toast.makeText(this@MainActivity, "Version : 1.0 ", Toast.LENGTH_SHORT).show()
                true
            }
        else -> super.onOptionsItemSelected(item)
    }
}
}