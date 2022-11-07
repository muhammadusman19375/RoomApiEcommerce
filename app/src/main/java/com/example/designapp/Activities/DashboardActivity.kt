package com.example.designapp.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.designapp.*
import com.example.designapp.Fragments.PageAdapter
import com.example.designapp.Interfaces.ApiInterface
import com.example.designapp.Retrofit.ProductsResponse
import com.example.designapp.Retrofit.RetrofitInstance
import com.example.designapp.RoomDB.AppDatabase
import com.example.designapp.RoomDB.User
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    private var tab_layout: TabLayout? = null
    private var view_pager: ViewPager2? = null
    private var tv_user_name: TextView? = null
    private var tv_view_all: TextView? = null
    private var tv_title_1: TextView? = null
    private var tv_title_2: TextView? = null
    private var tv_title_3: TextView? = null
    private var tv_rating_1: TextView? = null
    private var tv_rating_2: TextView? = null
    private var tv_rating_3: TextView? = null
    private var img_1: ImageView? = null
    private var img_2: ImageView? = null
    private var img_3: ImageView? = null
    private var tv_nav_drawer: ImageView? = null
    private var drawer_layout: DrawerLayout? = null
    private var nav_view: NavigationView? = null
    private var progress_bar: ProgressBar? = null
    lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_bar?.visibility = View.VISIBLE
        initTextFields()
        clickListeners()
        viewPager2()
        getNameFromDB()
        getProductsFromApi()
        gotoOtherActivity()




    }

    private fun initTextFields() {
        tab_layout = findViewById(R.id.tabLayout)
        tv_user_name = findViewById(R.id.tv_user_name)
        tv_view_all = findViewById(R.id.tv_view_all)
        view_pager = findViewById(R.id.viewPager)
        tv_title_1 = findViewById(R.id.tv_title_1)
        tv_title_2 = findViewById(R.id.tv_title_2)
        tv_title_3 = findViewById(R.id.tv_title_3)
        tv_rating_1 = findViewById(R.id.tv_rating_1)
        tv_rating_2 = findViewById(R.id.tv_rating_2)
        tv_rating_3 = findViewById(R.id.tv_rating_3)
        img_1 = findViewById(R.id.img_1)
        img_2 = findViewById(R.id.img_2)
        img_3 = findViewById(R.id.img_3)
        tv_nav_drawer = findViewById(R.id.imageViewMenu)
        drawer_layout = findViewById(R.id.drawer_layout)
        nav_view = findViewById(R.id.navigationView)
        progress_bar = findViewById(R.id.progress_bar)
    }

    private fun clickListeners() {

        tv_nav_drawer?.setOnClickListener {
            drawer_layout?.openDrawer(Gravity.LEFT)
        }
        nav_view?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menMenu -> Toast.makeText(this@DashboardActivity, "Men Clicked", Toast.LENGTH_SHORT).show()
                R.id.womenMenu -> Toast.makeText(this@DashboardActivity, "Women clicked", Toast.LENGTH_SHORT).show()
                R.id.jewleryMenu -> Toast.makeText(this@DashboardActivity, "Jewlery clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    private fun viewPager2() {
        view_pager!!.adapter = PageAdapter(this)
        TabLayoutMediator(tab_layout!!,view_pager!!) {tab, index ->
            tab.text = when(index){
                0 -> "Men"
                1 -> "Women"
                2 -> "Jewlery"
                else -> throw Resources.NotFoundException()
            }
        }.attach()
    }

    private fun getNameFromDB() {
        var user_email: String? = intent.getStringExtra("email")

        Thread(Runnable {
            run {
                val db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"UserDatabase").build()
                var userDao = db.userDao()
                var list: List<User> = userDao.getName(user_email.toString())

                for(i in 0..list.size-1){
                    tv_user_name?.text = list[i].firstName+" "+list[i].lastName
                }
            }
        }).start()

    }

    private fun getProductsFromApi() {
        var apiInterface: ApiInterface? = RetrofitInstance().getRetrofit()?.create(ApiInterface::class.java)

        apiInterface?.getDetails()?.enqueue(object: Callback<List<ProductsResponse>> {
            override fun onResponse(call: Call<List<ProductsResponse>>, response: Response<List<ProductsResponse>>) {
                if(response.body() != null){
                    progress_bar?.visibility = View.INVISIBLE
                    var list: List<ProductsResponse> = response.body()!!

                    tv_title_1?.setText(list[6].title.toString())
                    tv_rating_1?.setText(list[6].rating.rate.toString())
                    Picasso
                        .get()
                        .load(list[6].image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .fit()
                        .into(img_1)

                    tv_title_2?.setText(list[13].title.toString())
                    tv_rating_2?.setText(list[13].rating.rate.toString())
                    Picasso
                        .get()
                        .load(list[13].image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .fit()
                        .into(img_2)

                    tv_title_3?.setText(list[19].title.toString())
                    tv_rating_3?.setText(list[19].rating.rate.toString())
                    Picasso
                        .get()
                        .load(list[19].image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .fit()
                        .into(img_3)
                }
            }

            override fun onFailure(call: Call<List<ProductsResponse>>, t: Throwable) {

            }

        })
    }

    private fun gotoOtherActivity() {
        tv_view_all?.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,ViewAllProductsActivity::class.java))
        }
    }


}