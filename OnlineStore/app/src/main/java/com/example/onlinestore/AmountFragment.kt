package com.example.onlinestore

import android.app.AlertDialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AmountFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_amount, container, false)

        val edtEnterAmount: EditText = rootView.findViewById(R.id.edtEnterAmount)
        val btnAddToCart: ImageButton = rootView.findViewById(R.id.btnAddToCart)

        btnAddToCart.setOnClickListener {

            val ptoURL = "http://192.168.162.110/OnlineStoreApp/insert_temporary_order.php?" +
                    "email=${Person.email}" +
                    "&product_id=$" +
                    "{Person.addToCartProductID}&amount=${edtEnterAmount.text.toString()}"
            val requestQ = Volley.newRequestQueue(activity)
            val stringRequest = StringRequest(Request.Method.GET, ptoURL,
                { response ->

                    val intent = Intent(activity, CartProductsActivity::class.java)
                    startActivity(intent)

            }, { error ->
                    val dialogBuilder = AlertDialog.Builder(activity)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
            })

            requestQ.add(stringRequest)

        } // end of btnAddToCart.setOnClickListener

        return rootView

    } // end of onCreateView

} // end class AmountFragment

