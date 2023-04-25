package com.example.jedbookshelf.data

import com.example.jedbookshelf.model.BookShelf
import com.example.jedbookshelf.network.JedBookShelfApiService

interface JedBookShelfRepository {
    suspend fun getBookShelf(searchTerm: String): List<BookShelf>
    suspend fun getBookById(volumeId: String): BookShelf
}

class DefaultJedBookShelfRepository(private val jedBookShelfApiService: JedBookShelfApiService) :
    JedBookShelfRepository {
    override suspend fun getBookShelf(searchTerm: String): List<BookShelf> {
        return jedBookShelfApiService.getBookShelfs(searchTerm).items
    }

    override suspend fun getBookById(volumeId: String): BookShelf {
        return jedBookShelfApiService.getBookById(volumeId)
    }
}