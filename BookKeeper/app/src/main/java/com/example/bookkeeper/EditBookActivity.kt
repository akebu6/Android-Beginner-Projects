package com.example.bookkeeper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EditBookActivity : AppCompatActivity() {
    var id: String? = null
    var authorName: TextView = findViewById(R.id.authorName)
    var bookName: TextView = findViewById(R.id.bookName)

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        val bundle: Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("id")
            val book: String = bundle.getString("book")!!
            val author: String = bundle.getString("author")!!

            authorName.text = author
            bookName.text = book
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val updatedAuthor = authorName.text.toString()
            val updatedBook = bookName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(ID, id)
            resultIntent.putExtra(UPDATED_AUTHOR, updatedAuthor)
            resultIntent.putExtra(UPDATED_BOOK, updatedBook)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            finish()
        }
    }

    companion object {
        const val ID = "book_id"
        const val UPDATED_AUTHOR = "updated_author"
        const val UPDATED_BOOK = "updated_book"
    }
}
