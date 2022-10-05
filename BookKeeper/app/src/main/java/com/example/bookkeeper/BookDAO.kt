package com.example.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDAO {
    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

//    @Query("SELECT * FROM books")
//    fun getAllBooks(): LiveData<List<Book>>

    @get:Query("SELECT * FROM books")
    val allBooks: LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE book LIKE :searchString OR author LIKE :searchString")
    fun getBooksByBookOrAuthor(searchString: String): LiveData<List<Book>>

}
