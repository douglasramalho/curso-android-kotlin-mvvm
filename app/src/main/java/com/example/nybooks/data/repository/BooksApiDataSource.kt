package com.example.nybooks.data.repository

import com.example.nybooks.data.ApiService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class BooksApiDataSource : BooksRepository {

    override suspend fun getBooks(): BooksResult<Book> {
        return try {
            val bookResponse = ApiService.service.getBooks()
            val books: MutableList<Book> = mutableListOf()
            for (result in bookResponse.bookResults) {
                val book = result.bookDetailResponses[0].getBookModel()
                books.add(book)
            }

            BooksResult.Success(books)
        } catch (throwable: Throwable) {
            if (throwable is HttpException) {
                BooksResult.ApiError(401)
            } else BooksResult.ServerError
        }
    }
}