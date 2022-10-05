package com.example.bookkeeper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val resultIntent = Intent()
            if (TextUtils.isEmpty(findViewById<TextView>(R.id.authorName).text) ||
                TextUtils.isEmpty(findViewById<TextView>(R.id.bookName).text)
            ) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                val book = findViewById<TextView>(R.id.authorName).text.toString()
                val author = findViewById<TextView>(R.id.bookName).text.toString()
                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, book)
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            finish()
        }
    }

    companion object {
        const val NEW_AUTHOR = "new_author"
        const val NEW_BOOK = "new_book"
    }
}
