package com.example.designapp.Retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.Interfaces.iOnItemClickListener
import com.example.designapp.R
import com.squareup.picasso.Picasso

class MyAdapter(private val dataList: List<ProductsResponse>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: iOnItemClickListener

    fun setOnItemClickListener(listener: iOnItemClickListener){
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_row_design,parent,false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text = dataList[position].title
        holder.tv_rating.text = dataList[position].rating.rate.toString()

        Picasso
            .get()
            .load(dataList[position].image)
            .placeholder(R.drawable.ic_launcher_background)
            .fit()
            .into(holder.imageView)

//        holder.imageView.setOnClickListener {
//            onItemClickListener.onItemClick(position)
//        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(ItemView: View,listener: iOnItemClickListener) : RecyclerView.ViewHolder(ItemView){
        val tv_title = ItemView.findViewById<TextView>(R.id.tv_title14)
        val imageView = ItemView.findViewById<ImageView>(R.id.imageView13)
        val tv_rating = ItemView.findViewById<AppCompatButton>(R.id.tv_rating16)

        init{
            ItemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }
}