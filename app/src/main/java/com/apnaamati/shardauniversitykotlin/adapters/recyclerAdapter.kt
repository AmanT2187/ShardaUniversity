package com.apnaamati.shardauniversitykotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.apnaamati.shardauniversitykotlin.R
import com.apnaamati.shardauniversitykotlin.collectionsModel
import com.apnaamati.shardauniversitykotlin.models.notesModel
import com.bumptech.glide.Glide
import java.util.ArrayList
import android.content.ActivityNotFoundException

import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri

import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.apnaamati.shardauniversitykotlin.MainActivity2
import com.apnaamati.shardauniversitykotlin.notesPage
import java.io.File
import androidx.core.content.ContextCompat.startActivity
class recyclerAdapter (private val notesList: ArrayList<notesModel>, private val context: Context):
    RecyclerView.Adapter<recyclerAdapter.myViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): recyclerAdapter.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notes_file,parent,false)
        return recyclerAdapter.myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recyclerAdapter.myViewHolder, position: Int) {
        val notes = notesList[position]

        //Loading notes image
        Glide.with(context)
            .load(notes.url.toString())
            .fitCenter()
            .into(holder.image)

        //setting up notes tittle
        holder.title.setText(notes.title.toString())

        //setting otes description
        holder.descp.setText(notes.desp.toString())

        //handling on click event on nites
        holder.notesView.setOnClickListener {
            val intent2 = Intent(context, notesPage::class.java)
            intent2.putExtra("link2", notes.link.toString())
            context.startActivity(intent2)
        }

        //handling rating feature
        holder.rate.setOnClickListener {
            holder.rate.setImageResource(R.drawable.ic_baseline_star_rate_24)
            var int = 0
            holder.rateCount.setText(" " + ++int + " ")
            Toast.makeText(context, "Thanks For rating!! ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.imageView)
        val rate : ImageView = itemView.findViewById(R.id.btnRate)
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val rateCount : TextView = itemView.findViewById(R.id.rateCount)
        val descp : TextView = itemView.findViewById(R.id.tvDescription)
        val notesView : CardView = itemView.findViewById(R.id.notesView)
    }
}