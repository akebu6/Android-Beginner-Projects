package com.example.onlinestore

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import java.math.BigDecimal

class FinaliseShoppingActivity : AppCompatActivity() {

    var totalPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalise_shopping)

        val btnPaymentProcessing: Button = findViewById(R.id.btnPaymentProcessing)

        val calculatePaymentURL = "http://192.168.162.110/OnlineStoreApp/calculate_total_price.php?" +
                "invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"
        val requestQ = Volley.newRequestQueue(this@FinaliseShoppingActivity)
        val stringRequest = StringRequest(Request.Method.GET, calculatePaymentURL,
            { response ->

                btnPaymentProcessing.text = "Pay $$response via PayPal"
                totalPrice = response.toLong()

        }, { error ->
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        }) // end of StringRequest

        requestQ.add(stringRequest) // end of requestQ.add(stringRequest)

        // paypal configuration
        val paypalConfig: PayPalConfiguration = PayPalConfiguration().environment(
            PayPalConfiguration.ENVIRONMENT_SANDBOX).
            clientId(PayPalIntegration.clientID)
        val payPalService = Intent(this@FinaliseShoppingActivity,
            PayPalService::class.java)
        payPalService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        startService(payPalService)

        btnPaymentProcessing.setOnClickListener {

            val paypalProcessing = PayPalPayment(BigDecimal.valueOf(totalPrice),
                "USD", "Online Store Payment", PayPalPayment.PAYMENT_INTENT_SALE)
            val payPalPaymentIntent = Intent(this@FinaliseShoppingActivity,
                PaymentActivity::class.java)
            payPalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,
                paypalConfig)
            payPalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, paypalProcessing)
            startActivityForResult(payPalPaymentIntent, 1000)

        }

    } // end of onCreate method

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {

            if (resultCode == Activity.RESULT_OK) {

                val intent = Intent(this@FinaliseShoppingActivity,
                    HomeScreen::class.java)
                startActivity(intent)

            } else {

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Payment Failed, Please Try Again")
                dialogBuilder.create().show()

            } // end of else block

        } // end of if block

    } // end of onActivityResult method

    override fun onDestroy() {
        super.onDestroy()

        stopService(Intent(this@FinaliseShoppingActivity, PayPalService::class.java))

    } // end of onDestroy method

}