package com.example.designapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.designapp.R
import com.example.designapp.Interfaces.ApiInterface
import com.example.designapp.Retrofit.ProductsResponse
import com.example.designapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenFragment : Fragment() {
    var menRcView: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_men, container, false)


        menRcView = view.findViewById(R.id.menRcView)
        var apiInterface: ApiInterface? = RetrofitInstance().getRetrofit()?.create(ApiInterface::class.java)

        apiInterface?.getDetails()?.enqueue(object : Callback<List<ProductsResponse>>{
            override fun onResponse(call: Call<List<ProductsResponse>>, response: Response<List<ProductsResponse>>) {
                if(response.body() != null) {
                    var list1: List<ProductsResponse> = response.body()!!
                    var list2: ArrayList<ProductsResponse> = ArrayList<ProductsResponse>()

                    for(i in 0..list1.size-1){
                        if(list1[i].category.equals("men's clothing")){
                            list2.add(list1[i])
                        }
                    }
                    var adapter = FragmentAdapter(list2 as List<ProductsResponse>)
                    var gridLayoutManager = GridLayoutManager(context,2)
                    menRcView?.layoutManager = gridLayoutManager
                    menRcView?.adapter = adapter


//                    var linearLayoutManager = LinearLayoutManager(context)
//                    menRcView?.layoutManager = linearLayoutManager
//                    menRcView?.adapter = adapter
                }
                else{
                    Toast.makeText(context, "List is empty", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ProductsResponse>>, t: Throwable) {
                Toast.makeText(context," "+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }
}