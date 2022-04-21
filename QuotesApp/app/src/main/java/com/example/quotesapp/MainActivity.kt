package com.example.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.quotesapp.networking.JSONDataRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://api.quotable.io/"

class MainActivity : AppCompatActivity() {

    private var mProgressBar: ProgressBar? = null
    private var mQuoteText: TextView? = null
    private var mQuoteAuthor: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mProgressBar = findViewById(R.id.progressBar)
        mQuoteText = findViewById(R.id.txtQuoteText)
        mQuoteAuthor = findViewById(R.id.txtQuoteAuthor)

        displayRandomQuote()

        findViewById<RelativeLayout>(R.id.myRelativeLayout).setOnClickListener() {

            displayRandomQuote()

        } // end of onClickListener

    } // end of onCreate function

    fun displayRandomQuote() {

        mProgressBar?.visibility = View.VISIBLE
        mQuoteText?.visibility = View.GONE
        mQuoteAuthor?.visibility = View.GONE

        // creating retrofit object
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JSONDataRequest :: class.java)


        GlobalScope.launch(Dispatchers.IO) {

            val response = retrofitBuilder.getQuotes().awaitResponse()

            try {

                if (response.isSuccessful) {

                    val jsonData = response.body()!!

                    // logging for debugging purposes
                    Log.i("MainActivity", jsonData.toString())

                    withContext(Dispatchers.Main) {

                        mProgressBar?.visibility = View.GONE
                        mQuoteText?.visibility = View.VISIBLE
                        mQuoteAuthor?.visibility = View.VISIBLE

                        mQuoteText?.text = jsonData.content
                        mQuoteAuthor?.text = jsonData.author

                    } // end of withContext coroutines

                } // end of if block

            } catch (e: Exception) {

                e.printStackTrace()

            } // end of catch block

        } // end of globalScope block

    } // end of displayRandomQuote function

} // end of main class