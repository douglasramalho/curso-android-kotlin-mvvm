package com.example.nybooks.data

import com.example.nybooks.data.model.Book

sealed class BooksResult<T> {
    class Success<T>(val books: List<T>) : BooksResult<T>()
    class ApiError(val statusCode: Int) : BooksResult<Book>()
    object ServerError : BooksResult<Book>()
}