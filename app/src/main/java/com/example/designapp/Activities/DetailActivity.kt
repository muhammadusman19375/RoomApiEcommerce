package com.example.designapp.Activities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.designapp.R
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private var image_view: ImageView? = null
    private var tv_price: TextView? = null
    private var tv_title: TextView? = null
    private var tv_description: TextView? = null
    private var btn_add_to_cart: Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialize()
        getPastActivityData()


    }

    private fun initialize() {
        image_view = findViewById(R.id.image_view)
        tv_price = findViewById(R.id.tv_price)
        tv_title = findViewById(R.id.tv_title)
        tv_description = findViewById(R.id.tv_description)
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart)
    }

    private fun getPastActivityData() {
        var title: String? = intent.getStringExtra("title")
        var price: String? = intent.getStringExtra("price")
        var description: String? = intent.getStringExtra("description")
        var imgUrl: String? = intent.getStringExtra("image")


/*        var extras: Bundle? = intent.extras
        var byteArray: ByteArray? = extras?.getByteArray("image")
        var bmp: Bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray!!.size)

        image_view?.setImageBitmap(bmp)*/

        tv_price?.setText("Price : "+price)
        tv_title?.setText(title)
        tv_description?.setText(description)
        Picasso
            .get()
            .load(imgUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image_view)
    }
}