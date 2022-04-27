package com.example.onlinestore

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class FetchEProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_eproducts)

        val productsRV: RecyclerView = findViewById(R.id.productsRV)
        val txtBrandName: TextView = findViewById(R.id.txtBrandName)

        val selectedBrand = intent.getStringExtra("BRAND")
        txtBrandName.text = "$selectedBrand Products"

        val productsList = ArrayList<EProduct>()

        val productsURL = "http://192.168.162.110//OnlineStoreApp/" +
                "fetch_products.php?brand=$selectedBrand"
        val requestQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, productsURL, null,
            { response ->
                for (productJsonObject in 0.until(response.length())) {
                    productsList.add(EProduct(
                        response.getJSONObject(productJsonObject).getInt("id"),
                        response.getJSONObject(productJsonObject).getString("name"),
                        response.getJSONObject(productJsonObject).getInt("price"),
                        response.getJSONObject(productJsonObject).getString("picture")
                    ))

                }

                val pAdapter = EProductAdapter(this@FetchEProductsActivity, productsList)
                productsRV.layoutManager = LinearLayoutManager(this@FetchEProductsActivity)
                productsRV.adapter = pAdapter

            }, { error ->
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()
            })

        requestQ.add(jsonAR)

    } // end of `onCreate`

} // end of `FetchEProductsActivity`