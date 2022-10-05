@file:Suppress("DEPRECATION")

package com.example.bookkeeper

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchResultActivity : AppCompatActivity(), BookAdapterList.OnDeleteClickListener {

    private lateinit var searchViewModel: SearchViewModel
    private var bookListAdapter: BookAdapterList? = null
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        findViewById<FloatingActionButton>(R.id.addIcon).visibility = View.INVISIBLE

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        bookListAdapter = BookAdapterList(this, this)
        recyclerView.adapter = bookListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {

            val searchQuery = intent.getStringExtra(SearchManager.QUERY)

            Log.i(TAG, "Search Query is $searchQuery")

            searchViewModel.getBooksByBookOrAuthor("%$searchQuery%")?.observe(
                this
            ) { books ->
                books?.let { bookListAdapter!!.setBooks(books) }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == UPDATE_BOOK_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Code to edit book
            val bookId = data!!.getStringExtra(EditBookActivity.ID)
            val authorName = data.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
            val bookName = data.getStringExtra(EditBookActivity.UPDATED_BOOK)

            val book = Book(bookId!!, authorName!!, bookName!!)
            searchViewModel.update(book)

            Toast.makeText(applicationContext, R.string.updated, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDeleteClickListener(myBook: Book) {
        searchViewModel.delete(myBook)
        Toast.makeText(applicationContext, R.string.deleted, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val UPDATE_BOOK_ACTIVITY_REQUEST_CODE = 2
    }
}
