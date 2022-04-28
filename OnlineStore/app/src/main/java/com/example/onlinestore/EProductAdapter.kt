package com.example.onlinestore

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class EProductAdapter(var context: Context, var arrayList: ArrayList<EProduct>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val productView = LayoutInflater.from(context).inflate(
            R.layout.e_product_row, parent, false)

        return ProductViewHolder(productView)

    } // end of onCreateViewHolder method

    override fun getItemCount(): Int {

        return arrayList.size

    }  // end of getItemCount method

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initialiseUIComponents(
            arrayList[position].id,
            arrayList[position].name,
            arrayList[position].price,
            arrayList[position].picture)

    } // end of onBindViewHolder method

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val imgAdd: ImageView = itemView.findViewById(R.id.imgAdd)
        val txtID: TextView = itemView.findViewById(R.id.txtID)
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)

        fun initialiseUIComponents(id: Int, name: String, price: Int, image: String) {
            txtID.text = id.toString()
            txtName.text = name
            txtPrice.text = price.toString()

            var picURL = "http://192.168.162.110/OnlineStoreApp/osimages/"
            picURL  = picURL.replace(" ", "%20")

            Picasso.get().load(picURL + image).into(imgProduct)

            imgAdd.setOnClickListener {

                Person.addToCartProductID = id
                val amountFragment = AmountFragment()
                val fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager, "AmountFragment")

            } // end of onClickListener

        } // end of initialiseUIComponents()

    } // end of ViewHolder class

} // end of EProductAdapter class