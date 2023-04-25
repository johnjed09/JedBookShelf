package com.example.jedbookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.jedbookshelf.R
import com.example.jedbookshelf.ui.screens.JedBookShelfViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jedbookshelf.ui.screens.HomeScreen

@Composable
fun JedBookShelfApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val jedBookShelfViewModel: JedBookShelfViewModel =
                viewModel(factory = JedBookShelfViewModel.Factory)
            HomeScreen(
                jedBookShelfUiState = jedBookShelfViewModel.jedBookShelfUiState
            )
        }
    }
}