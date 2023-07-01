package com.example.bookkeeper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapterList(
    private val context: Context,
    private val onDeleteClickListener: OnDeleteClickListener
) : RecyclerView.Adapter<BookAdapterList.BookViewHolder>() {
    interface OnDeleteClickListener {
        fun onDeleteClickListener(myBook: Book)
    }

    private var bookList: List<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.author, book.book, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = bookList.size

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0

        fun setData(author: String, book: String, position: Int) {
            itemView.findViewById<TextView>(R.id.tvAuthor).text = author
            itemView.findViewById<TextView>(R.id.tvBook).text = book
            this.pos = position
        }

        fun setListeners() {
            itemView.findViewById<ImageView>(R.id.ivRowEdit).setOnClickListener {
                val intent = Intent(context, EditBookActivity::class.java)
                intent.putExtra("id", bookList[pos].id)
                intent.putExtra("author", bookList[pos].author)
                intent.putExtra("book", bookList[pos].book)
                (context as Activity).startActivityForResult(
                    intent, MainActivity.UPDATE_BOOK_ACTIVITY_REQUEST_CODE
                )
            }

            itemView.findViewById<ImageView>(R.id.ivRowDelete).setOnClickListener {
                onDeleteClickListener.onDeleteClickListener(bookList[pos])
            }
        }
    }
}
