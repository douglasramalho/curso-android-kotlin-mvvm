package com.example.nybooks.data.repository

import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book

interface BooksRepository {

    suspend fun getBooks(): BooksResult<Book>
}