package com.example.designapp.Activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.room.Room
import com.example.designapp.RoomDB.AppDatabase
import com.example.designapp.RoomDB.User
import com.example.designapp.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val TAG = "TAG"
    private var isAllowed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAlreadyRegister.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
        selectDate()
        insertDataInDatabase()
        checkEmailExists()

    }

    private fun insertDataInDatabase() {

        binding.btnRegister.setOnClickListener {


            var firstName = binding.inputFirstName.text.toString()
            var lastName = binding.inputLastName.text.toString()
            var dateOfBirth = binding.inputDOB.text.toString()
            var email = binding.inputEmail.text.toString()
            var password = binding.inputPassword.text.toString()
            var confirmPassword = binding.inputConfirmPassword.text.toString()

            if (firstName.isEmpty()) {
                binding.inputFirstName.setError("Field cannot be empty")
            } else if (lastName.isEmpty()) {
                binding.inputLastName.setError("Field cannot be empty")
            } else if (dateOfBirth.isEmpty()) {
                binding.inputDOB.setError("Field cannot be empty")
            } else if (email.isEmpty()) {
                binding.inputEmail.setError("Field cannot be empty")
            } else if (password.length <= 7) {
                binding.inputPassword.setError("Minumum password length should be eight")
            } else if (password.isEmpty()) {
                binding.inputPassword.setError("Field cannot be empty")
            } else if (confirmPassword.isEmpty()) {
                binding.inputConfirmPassword.setError("Field cannot be empty")
            } else if (confirmPassword != password) {
                binding.inputConfirmPassword.setError("Password does not match")
            } else {
                if (emailValidation()) {
                    if (passwordValidation()) {
                        if(isAllowed){
                            Thread(Runnable {
                                run {
                                    val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "UserDatabase").build()

                                    val userDao = db.userDao()
                                    val user = User(uid = null, firstName, lastName, dateOfBirth, email, password, confirmPassword)

                                    userDao.insertAll(user)
                                    runOnUiThread(Runnable {
                                        run {
                                            Toast.makeText(this@RegisterActivity, "Registered successfully", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                    binding.inputFirstName.setText("")
                                    binding.inputLastName.setText("")
                                    binding.inputDOB.setText("")
                                    binding.inputEmail.setText("")
                                    binding.inputPassword.setText("")
                                    binding.inputConfirmPassword.setText("")
                                }
                            }).start()
                        }
                        else{
                            Toast.makeText(this@RegisterActivity, "Enter new email", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    private fun selectDate() {

        var calendar: Calendar = Calendar.getInstance()
        var year: Int = calendar.get(Calendar.YEAR)
        var month: Int = calendar.get(Calendar.MONTH)
        var day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        binding.inputDOB.setOnClickListener {
            var datePickerDialog: DatePickerDialog = DatePickerDialog(
                this@RegisterActivity,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    var newMonth: Int = month + 1
                    var date: String =
                        dayOfMonth.toString() + "/" + newMonth.toString() + "/" + year
                    binding.inputDOB.setText(date)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun passwordValidation(): Boolean {
        var passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$".toRegex()
        var password = binding.inputPassword.text
        var confirmPassword = binding.inputConfirmPassword.text.toString()

        if (password.matches(passwordPattern)) {
            return true
        } else {
            binding.inputPassword.setError("Password must contain alphabet,numeric,special symbols")
            return false
        }
    }

    private fun emailValidation(): Boolean {
        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        var email = binding.inputEmail.text.toString()
        if (email.matches(emailPattern)) {
            return true
        } else {
            binding.inputEmail.setError("Invalid E-mail Pattern")
            return false
        }
    }

    private fun checkEmailExists(){
        binding.inputEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Thread(Runnable {
                    run {
                        val db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"UserDatabase").build()
                        val userDao = db.userDao()

                        if(userDao.isTaken(binding.inputEmail.text.toString())){
                            isAllowed = false
                            runOnUiThread(Runnable {
                                run {
                                    binding.inputEmail.setError("Email is already taken")
                                }
                            })
                        }
                        else{
                            isAllowed = true
                        }
                    }
                }).start()
            }

        })
    }
}