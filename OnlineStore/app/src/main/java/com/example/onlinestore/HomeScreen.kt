package com.example.onlinestore

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val brandsListView: ListView = findViewById(R.id.brandsListView)

        val brandsURL = "http://192.168.162.110/OnlineStoreApp/fetch_brands.php"
        val brandsList = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@HomeScreen)
        val jsonAR = JsonArrayRequest(Request.Method.GET, brandsURL, null, {
            response ->

            for (jsonObject in 0.until(response.length())) {
                brandsList.add(response.getJSONObject(jsonObject).getString("brand"))
            }

            val adapter = ArrayAdapter(this@HomeScreen,
                android.R.layout.simple_list_item_1, brandsList)
            brandsListView.adapter = adapter

        }, {
            error ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })

        requestQ.add(jsonAR)

        brandsListView.setOnItemClickListener { _, _, i, _ ->

            val tappedBrand = brandsList[i]
            val brandIntent = Intent(this@HomeScreen, FetchEProductsActivity::class.java)
            brandIntent.putExtra("BRAND", tappedBrand)
            startActivity(brandIntent)

        } // end of setOnItemClickListener

    } // end onCreate method

} // end class HomeScreen
