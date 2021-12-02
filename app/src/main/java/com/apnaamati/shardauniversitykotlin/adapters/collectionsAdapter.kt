package com.apnaamati.shardauniversitykotlin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.apnaamati.shardauniversitykotlin.MainActivity2
import com.apnaamati.shardauniversitykotlin.R
import com.apnaamati.shardauniversitykotlin.collectionsModel
import com.bumptech.glide.Glide
import java.util.ArrayList

class collectionsAdapter(private val mCollectionsList: ArrayList<collectionsModel>, private val context: Context):
    RecyclerView.Adapter<collectionsAdapter.myViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): collectionsAdapter.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.catogry_item,parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: collectionsAdapter.myViewHolder, position: Int) {
        val images = mCollectionsList[position]
        Glide.with(context)
            .load(images.url)
            .fitCenter()
            .into(holder.image)

        holder.image.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("tittle", images.title.toString())
            intent.putExtra("link", images.link.toString())
           context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mCollectionsList.size
    }
    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.itemImage)
    }
}
