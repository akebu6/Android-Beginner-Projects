package com.example.onlinestore

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signup_activity_btnSignup: Button = findViewById(R.id.signup_activity_btnSignup)
        val signup_activity_edtSignupPassword: EditText = findViewById(
            R.id.signup_activity_edtSignupPassword)
        val signup_activity_edtSignupConfirmPassword: EditText = findViewById(
            R.id.signup_activity_edtSignupConfirmPassword)
        val signup_activity_edtLoginEmail: EditText = findViewById(R.id.signup_activity_edtLoginEmail)
        val signup_activity_edtSignupUsername: EditText = findViewById(
            R.id.signup_activity_edtSignupUsername)
        val signup_activity_btnLogin: Button = findViewById(R.id.signup_activity_btnLogin)

        signup_activity_btnSignup.setOnClickListener {

            if (signup_activity_edtSignupPassword.text.toString() ==
                signup_activity_edtSignupConfirmPassword.text.toString()) {

                // registration process
                val signUpURL = "http://192.168.162.110//OnlineStoreApp/join_new_user.php?email=" +
                        signup_activity_edtLoginEmail.text.toString() + "&username=" +
                        signup_activity_edtSignupUsername.text.toString() + "&password=" +
                        signup_activity_edtSignupPassword.text.toString()
                val requestQ = Volley.newRequestQueue(this@SignupActivity)
                val stringRequest = StringRequest(Request.Method.GET, signUpURL,
                    { response ->
                        if (response.equals("Registration Successful!")) {
                            val dialogBuilder = AlertDialog.Builder(this)
                            dialogBuilder.setTitle("Message")
                            dialogBuilder.setMessage(response)
                            dialogBuilder.create().show()
                        } else {
//                            val dialogBuilder = AlertDialog.Builder(this)
//                            dialogBuilder.setTitle("Message")
//                            dialogBuilder.setMessage(response)
//                            dialogBuilder.create().show()

                            Person.email = signup_activity_edtLoginEmail.text.toString()

                            Toast.makeText(this@SignupActivity, response, Toast.LENGTH_SHORT).show()

                            // connecting user to homeScreen
                            val homeIntent = Intent(this@SignupActivity, HomeScreen::class.java)
                            startActivity(homeIntent)
                        }
                    },
                    { error ->
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(error.message)
                        dialogBuilder.create().show()
                    })

                requestQ.add(stringRequest)

            } else {

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Error")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()

            }

        } // end of signup_activity_btnSignup.setOnClickListener

        signup_activity_btnLogin.setOnClickListener {

            finish()

        } // end of signup_activity_btnLogin.setOnClickListener

    } // end of onCreate method

} // end of SignupActivity class