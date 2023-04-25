package com.example.jedbookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookSearchResult(
    val items: List<BookShelf>
)

@Serializable
data class BookShelf(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks,
//    val authors: List<String>,
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)