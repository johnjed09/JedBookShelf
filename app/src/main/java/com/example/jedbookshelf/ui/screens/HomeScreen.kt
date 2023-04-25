package com.example.jedbookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jedbookshelf.R
import com.example.jedbookshelf.model.BookShelf
import com.example.jedbookshelf.model.ImageLinks
import com.example.jedbookshelf.model.VolumeInfo
import com.example.jedbookshelf.ui.theme.JedBookshelfTheme
import androidx.compose.foundation.lazy.grid.items

@Composable
fun HomeScreen(
    jedBookShelfUiState: JedBookShelfUiState, modifier: Modifier = Modifier
) {
    when (jedBookShelfUiState) {
        is JedBookShelfUiState.Loading -> LoadingScreen(modifier)
        is JedBookShelfUiState.Success -> JedBookShelf(modifier, jedBookShelfUiState.books)
        else -> ErrorScreen(modifier)
    }

}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun JedBookShelf(modifier: Modifier, books: List<BookShelf>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(items = books) { book ->
            Book(book = book)
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}

@Composable
fun Book(book: BookShelf, modifier: Modifier = Modifier) {
    val thumnnailHttps = book.volumeInfo.imageLinks.thumbnail.replace("http", "https")
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(thumnnailHttps)
                .crossfade(true).build(),
            contentDescription = stringResource(
                (R.string.jed_bookshelf_book)
            ),
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_connection_error),
            placeholder = painterResource(id = R.drawable.loading_img)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookShelf() {
    JedBookshelfTheme {
        val mockSingleData = BookShelf(
            id = "zyTCAlFPjgYC",
            volumeInfo = VolumeInfo(
                title = "The Google Story (2018 Updated Edition)",
                imageLinks = ImageLinks(thumbnail = "https://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE70cqvQKg_g1s32nzvaGhkZkv7GLJSIUi8FXovGfScHTJW2vigGqvudIgRFWytjtiZBfUMZsWoZ8CGjarfoHEWYgYig1GA0wKU-q8BT8axl3q1e2_wesn2zqH3hFCZaRcSszy58F&source=gbs_api")
            )
        )
        Book(mockSingleData)
    }
}