package com.example.designapp.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.R
import com.example.designapp.Retrofit.MyAdapter
import com.example.designapp.Retrofit.ProductsResponse
import com.squareup.picasso.Picasso

class FragmentAdapter(private val dataList: List<ProductsResponse>): RecyclerView.Adapter<FragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_row_design,parent,false)
        return FragmentAdapter.ViewHolder(view)
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
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tv_title = ItemView.findViewById<TextView>(R.id.tv_title14)
        val imageView = ItemView.findViewById<ImageView>(R.id.imageView13)
        val tv_rating = ItemView.findViewById<AppCompatButton>(R.id.tv_rating16)
    }
}