package com.example.jedbookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jedbookshelf.JedBookShelfApplication
import com.example.jedbookshelf.data.JedBookShelfRepository
import com.example.jedbookshelf.model.BookShelf
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface JedBookShelfUiState {
    data class Success(val books: List<BookShelf>) : JedBookShelfUiState
    object Error : JedBookShelfUiState
    object Loading : JedBookShelfUiState
}

class JedBookShelfViewModel(private val jedBookShelfRepository: JedBookShelfRepository) :
    ViewModel() {

    var jedBookShelfUiState: JedBookShelfUiState by mutableStateOf(JedBookShelfUiState.Loading)
        private set

    init {
        getBookShelfs()
    }

    private fun getBookShelfs() {
        viewModelScope.launch {
            jedBookShelfUiState = try {
                val listResult = jedBookShelfRepository.getBookShelf("cicero")
                Log.d("listResult", listResult.toString())

                val bookResults = mutableListOf<BookShelf>()
                listResult.forEach { book ->
                    var isBookProcessed = false

                    for (bookResult in bookResults) {
                        if (bookResult.volumeInfo.title == book.volumeInfo.title) {
                            isBookProcessed = true
                            break
                        }
                    }

                    if (isBookProcessed) {
                        return@forEach
                    }

                    bookResults.add(jedBookShelfRepository.getBookById(book.id))

                }
                Log.d("bookResult", bookResults.toString())
                JedBookShelfUiState.Success(bookResults)
            } catch (e: IOException) {
                JedBookShelfUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as JedBookShelfApplication)
                val jedBookShelfRepository = application.container.jedBookShelfRepository
                JedBookShelfViewModel(jedBookShelfRepository)
            }
        }
    }
}