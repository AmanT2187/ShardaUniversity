package com.apnaamati.shardauniversitykotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.apnaamati.shardauniversitykotlin.R
import com.apnaamati.shardauniversitykotlin.slider
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class sliderAdapter (private val mSliderItems: ArrayList<slider>, private val context: Context? = null) :
    SliderViewAdapter<sliderAdapter.SliderAdapterVH>() {
    class SliderAdapterVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): sliderAdapter.SliderAdapterVH {
        val inflater = LayoutInflater.from(parent?.context).inflate(R.layout.slider_layout,parent,false)
        return SliderAdapterVH(inflater)
    }

    override fun onBindViewHolder(viewHolder: sliderAdapter.SliderAdapterVH?, position: Int) {
        val images = mSliderItems[position]
        if (context != null) {
            if (viewHolder != null) {
                Glide.with(context)
                    .load(images.url)
                    .fitCenter()
                    .into(viewHolder.image)
            }
        }
    }
}