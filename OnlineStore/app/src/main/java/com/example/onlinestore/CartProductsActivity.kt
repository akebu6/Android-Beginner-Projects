package com.example.onlinestore

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.reflect.Method

class CartProductsActivity : AppCompatActivity() { // end of CartProductsActivity
    // end of CartProductsActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

        val cartProductsListView: ListView = findViewById(R.id.cartProdcutsListView)

        val cartURL = "http://192.168.162.110//OnlineStoreApp/fetch_temporary_order.php?email=" +
                Person.email
        val cartProductsList = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@CartProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, cartURL, null,
            { response ->
                for (jsonObject in 0.until(response.length())) {
                    // getting the id, name, price, email, amount
                    cartProductsList.add("${response.getJSONObject(jsonObject).getInt("id")} " +
                            "\n ${response.getJSONObject(jsonObject).getString("name")} " +
                            "\n ${response.getJSONObject(jsonObject).getInt("price")} " +
                            "\n ${response.getJSONObject(jsonObject).getString("email")} " +
                            "\n ${response.getJSONObject(jsonObject).getInt("amount")}")

                    val cartProductsAdapter = ArrayAdapter(this@CartProductsActivity,
                        android.R.layout.simple_list_item_1, cartProductsList)
                    cartProductsListView.adapter = cartProductsAdapter
                }

            },

            { error -> val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()

            }) // end of jsonAR

        requestQ.add(jsonAR)

    } // end of onCreate method

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)

    } // end of onCreateOptionsMenu method

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.continueShopping) {

            val intent = Intent(this@CartProductsActivity, HomeScreen::class.java)
            startActivity(intent)

        } else if (item.itemId == R.id.declineOrder) {

            val deleteURL = "http://192.168.162.110//OnlineStoreApp/decline_order.php?email=${Person.email}"
            val requestQ = Volley.newRequestQueue(this@CartProductsActivity)
            val stringRequest = StringRequest(Request.Method.GET, deleteURL,
                { response->

                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)

                },

                { error->  val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()

                }) // end of stringRequest

            requestQ.add(stringRequest)

        } // end of else if

        return super.onOptionsItemSelected(item)

    } // end of onOptionsItemSelected method

}