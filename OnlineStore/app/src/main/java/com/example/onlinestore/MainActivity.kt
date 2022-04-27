package com.example.onlinestore

import android.app.AlertDialog
import android.app.Person
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val main_activity_btnSignup: Button = findViewById(R.id.main_activity_btnSignup)
        val main_activity_btnLogin: Button = findViewById(R.id.main_activity_btnLogin)
        val main_activity_edtLoginEmail: EditText = findViewById(R.id.main_activity_edtLoginEmail)
        val main_activity_edtLoginPassword: EditText = findViewById(R.id.main_activity_edtLoginPassword)

        main_activity_btnSignup.setOnClickListener {

            val signupIntent = Intent(this@MainActivity, SignupActivity::class.java)
            startActivity(signupIntent)

        } // end of main_activity_btnSignup.setOnClickListener

        main_activity_btnLogin.setOnClickListener {

            val loginURL = "http://192.168.162.110//OnlineStoreApp/login_app_user.php?email=" +
                    main_activity_edtLoginEmail.text.toString() + "&password=" +
                    main_activity_edtLoginPassword.text.toString()
            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginURL,
                { response ->

                    if (response.equals("Login Failed")) {

                        com.example.onlinestore.Person.email =
                            main_activity_edtLoginEmail.text.toString()

                        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()

                        // connecting user to homeScreen
                        val homeIntent = Intent(this@MainActivity, HomeScreen::class.java)
                        startActivity(homeIntent)

                    } else {

                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()
                    }
                },

                { error ->

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })

            requestQ.add(stringRequest)

        } // end of main_activity_btnLogin.setOnClickListener

    } // end onCreate method

} // end class MainActivity

