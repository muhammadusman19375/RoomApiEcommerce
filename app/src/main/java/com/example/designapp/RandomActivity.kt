package com.example.designapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class RandomActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.navigationView)
//
//
//        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navView.setNavigationItemSelectedListener {
//            when(it.itemId){
//
//                R.id.menMenu -> Toast.makeText(applicationContext, "Men fragment selected", Toast.LENGTH_SHORT).show()
//                R.id.womenMenu -> Toast.makeText(applicationContext, "Women fragment selected", Toast.LENGTH_SHORT).show()
//                R.id.jewleryMenu -> Toast.makeText(applicationContext, "Jewlery fragment selected", Toast.LENGTH_SHORT).show()
//                R.id.electronicMenu -> Toast.makeText(applicationContext, "Electronic fragment selected", Toast.LENGTH_SHORT).show()
//
//            }
//            true
//        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}