package com.example.designapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.designapp.RoomDB.AppDatabase
import com.example.designapp.RoomDB.User
import com.example.designapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var TAG: String = "TAG"

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            Thread(Runnable {
                run {
                    val db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"UserDatabase")
                        .build()

                    val userDao = db.userDao()

                    if(userDao.getCredentials(binding.inputEmail.text.toString(), binding.inputPassword.text.toString())){
                        val intent = Intent(this@LoginActivity,DashboardActivity::class.java)
                        intent.putExtra("email",binding.inputEmail.text.toString())
                        startActivity(intent)
                    }
                    else{
                        runOnUiThread(Runnable {
                            run {
                                Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                }
            }).start()
        }


    }
}