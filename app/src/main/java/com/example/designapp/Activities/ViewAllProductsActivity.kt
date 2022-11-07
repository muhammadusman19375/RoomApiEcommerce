package com.example.designapp.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.R
import com.example.designapp.Interfaces.ApiInterface
import com.example.designapp.Interfaces.iOnItemClickListener
import com.example.designapp.Retrofit.MyAdapter
import com.example.designapp.Retrofit.ProductsResponse
import com.example.designapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream

class ViewAllProductsActivity : AppCompatActivity(){
    var rc_view_products: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var itemClickListener: OnItemClickListener? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_products)
        rc_view_products = findViewById(R.id.rc_view_products)
        progressBar = findViewById(R.id.progressBar)
        progressBar?.visibility = View.VISIBLE

        getProductsFromApi()

    }

    private fun getProductsFromApi() {
        Thread(Runnable {
            run {
                var apiInterface: ApiInterface? = RetrofitInstance().getRetrofit()?.create(
                    ApiInterface::class.java)

                apiInterface?.getDetails()?.enqueue(object : retrofit2.Callback<List<ProductsResponse>>{
                    override fun onResponse(call: Call<List<ProductsResponse>>, response: Response<List<ProductsResponse>>) {
                        if(response.body() != null){

                            progressBar?.visibility = View.INVISIBLE
                            var list: List<ProductsResponse> = response.body()!!
                            var adapter = MyAdapter(list)
                            /* Setting the layout of the recycler view. */
                            var gridLayoutManager = GridLayoutManager(applicationContext,2)
                            rc_view_products?.layoutManager = gridLayoutManager
                            rc_view_products?.adapter = adapter
                            adapter.setOnItemClickListener(object: iOnItemClickListener{
                                override fun onItemClick(position: Int) {

                                    /* This is passing data from one activity to another. */
                                    var intent = Intent(this@ViewAllProductsActivity,DetailActivity::class.java)
                                    intent.putExtra("title",list[position].title)
                                    intent.putExtra("price",list[position].price.toString())
                                    intent.putExtra("description",list[position].description)
                                    intent.putExtra("image",list[position].image)
                                    startActivity(intent)
                                }

                            })
                        }
                    }

                    override fun onFailure(call: Call<List<ProductsResponse>>, t: Throwable) {
                        Toast.makeText(this@ViewAllProductsActivity, " "+t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }).start()
    }
}