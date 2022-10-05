@file:Suppress("DEPRECATION")

package com.example.bookkeeper

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class BookViewModel(application: Application) : AndroidViewModel(application) {
    val allBooks: LiveData<List<Book>>
    private val bookDao: BookDAO

    init {
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
        allBooks = bookDao.allBooks
    }

    fun insert(book: Book) {
        InsertAsyncTask(bookDao).execute(book)
    }

    fun update(book: Book) {
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book: Book) {
        DeleteAsyncTask(bookDao).execute(book)
    }

    companion object {
        private class InsertAsyncTask(private val bookDao: BookDAO) : AsyncTask<Book, Void, Void>() {
            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg params: Book): Void? {
                bookDao.insert(params[0])
                return null
            }
        }

        private class UpdateAsyncTask(private val bookDao: BookDAO) : AsyncTask<Book, Void, Void>() {
            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg params: Book): Void? {
                bookDao.update(params[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val bookDao: BookDAO) : AsyncTask<Book, Void, Void>() {
            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg params: Book): Void? {
                bookDao.delete(params[0])
                return null
            }
        }
    }
}
