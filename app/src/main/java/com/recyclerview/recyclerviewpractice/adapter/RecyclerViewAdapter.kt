package com.recyclerview.recyclerviewpractice.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.recyclerview.recyclerviewpractice.OnClickRowListener
import com.recyclerview.recyclerviewpractice.R
import com.recyclerview.recyclerviewpractice.adapter.RecyclerViewAdapter.RecyclerViewHolder
import com.recyclerview.recyclerviewpractice.model.ApiResponse
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(
    private val listener: OnClickRowListener,
//    private val courseDataArrayList: List<ApiResponse>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var recipeList = ArrayList<ApiResponse>()
    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(recipeList : List<ApiResponse>){
        this.recipeList = recipeList as ArrayList<ApiResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_list_view, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        val item = recipeList[position]
        holder.nameTV.text = item.name
        holder.caloriesTV.text = item.calories
        holder.headlineTV.text = item.headline
//        Picasso.get().load(item.thumb).into(holder.imageView)
        Glide.with(holder.itemView)
            .load(item.thumb)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            listener.onClickItem(position, item)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView
        val headlineTV: TextView
        val caloriesTV: TextView
        val imageView: ImageView

        init {
            nameTV = itemView.findViewById(R.id.tvRvName)
            headlineTV = itemView.findViewById(R.id.tvRvHeadline)
            caloriesTV = itemView.findViewById(R.id.tvRvCalories)
            imageView = itemView.findViewById(R.id.IVImage)
        }
    }
}