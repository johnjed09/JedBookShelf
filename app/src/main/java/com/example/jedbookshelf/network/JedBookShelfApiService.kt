package com.example.jedbookshelf.network

import com.example.jedbookshelf.model.BookSearchResult
import com.example.jedbookshelf.model.BookShelf
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JedBookShelfApiService {
    @GET("books/v1/volumes")
    suspend fun getBookShelfs(@Query("q") searchTerm: String): BookSearchResult

    @GET("books/v1/volumes/{volume_id}")
    suspend fun getBookById(@Path("volume_id") volumeId: String): BookShelf
}
